package com.example.payment.service;

import com.example.payment.dto.DeliveryExecutedMessage;
import com.example.payment.dto.DeliveryRejectedMessage;
import com.example.payment.dto.ProductReservedMessage;
import com.example.payment.entity.DeliveryReservationEntity;
import com.example.payment.entity.DeliveryStatus;
import com.example.payment.kafka.KafkaProducerService;
import com.example.payment.repository.DeliveryReservedRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeliveryService {

    private final DeliveryReservedRepository deliveryReservedRepository;
    private final KafkaProducerService kafkaProducerService;
//    private final SageCompensationService sageCompensationService;

    public void process(ProductReservedMessage message) {
        DeliveryReservationEntity deliveryReservationEntity;
        boolean isDeliverySuccess = deliverProduct(message.getOrderId());
        if (isDeliverySuccess) {
            log.info("Product successfully delivered: {}", message);
            deliveryReservationEntity = saveToDb(message, DeliveryStatus.DELIVERED);
            sendDeliverySucceed(message, deliveryReservationEntity);
        } else {
            log.warn("Product NOT delivered: {}", message);
            deliveryReservationEntity = saveToDb(message, DeliveryStatus.REJECTED_BY_DELIVERY);
            sendDeliveryRejected(message, deliveryReservationEntity);
        }
    }

    private void sendDeliverySucceed(ProductReservedMessage productReservedMessage, DeliveryReservationEntity deliveryReservationEntity) {
        DeliveryExecutedMessage deliveryExecutedMessage = DeliveryExecutedMessage.builder()
                .orderId(productReservedMessage.getOrderId())
                .deliveryId(deliveryReservationEntity.getId())
                .build();
        kafkaProducerService.sendDeliveryExecuted(deliveryExecutedMessage);
    }

    private void sendDeliveryRejected(ProductReservedMessage productReservedMessage, DeliveryReservationEntity deliveryReservationEntity) {
        DeliveryRejectedMessage message = DeliveryRejectedMessage.builder()
                .orderId(productReservedMessage.getOrderId())
                .deliveryId(deliveryReservationEntity.getId())
                .errorCode("Delivery rejected")
                .build();
        kafkaProducerService.sendDeliveryRejected(message);
    }

    public DeliveryReservationEntity saveToDb(ProductReservedMessage message, DeliveryStatus deliveryStatus) {
        DeliveryReservationEntity entity = DeliveryReservationEntity.builder()
                .orderId(message.getOrderId())
                .status(deliveryStatus)
                .build();
        DeliveryReservationEntity dbEntity = deliveryReservedRepository.save(entity);
        return dbEntity;
    }

    /**
     * @param orderId - from Order service
     * @return return false for all orderId divisible by 5+3.
     * Example: 7 - false, 12 - false, 2 - true
     */
    public boolean deliverProduct(Long orderId) {
        if (orderId < 5) {
            return true;
        }
        orderId += 3;
        return (orderId % 5L) != 0;
    }
}
