package com.example.notification.service;

import com.example.notification.dto.DeliveryExecutedMessage;
import com.example.notification.dto.DeliveryRejectedMessage;
import com.example.notification.dto.ProductReservedMessage;
import com.example.notification.entity.NotificationEntity;
import com.example.notification.entity.NotificationType;
import com.example.notification.kafka.KafkaProducerService;
import com.example.notification.repository.DeliveryReservedRepository;
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
        NotificationEntity notificationEntity;
        boolean isDeliverySuccess = deliverProduct(message.getOrderId());
        if (isDeliverySuccess) {
            log.info("Product successfully delivered: {}", message);
            notificationEntity = saveToDb(message, NotificationType.PAYMENT_EXECUTED);
            sendDeliverySucceed(message, notificationEntity);
        } else {
            log.warn("Product NOT delivered: {}", message);
            notificationEntity = saveToDb(message, NotificationType.REJECTED_BY_DELIVERY);
            sendDeliveryRejected(message, notificationEntity);
        }
    }

    private void sendDeliverySucceed(ProductReservedMessage productReservedMessage, NotificationEntity notificationEntity) {
        DeliveryExecutedMessage deliveryExecutedMessage = DeliveryExecutedMessage.builder()
                .orderId(productReservedMessage.getOrderId())
                .deliveryId(notificationEntity.getId())
                .build();
        kafkaProducerService.sendDeliveryExecuted(deliveryExecutedMessage);
    }

    private void sendDeliveryRejected(ProductReservedMessage productReservedMessage, NotificationEntity notificationEntity) {
        DeliveryRejectedMessage message = DeliveryRejectedMessage.builder()
                .orderId(productReservedMessage.getOrderId())
                .deliveryId(notificationEntity.getId())
                .errorCode("Delivery rejected")
                .build();
        kafkaProducerService.sendDeliveryRejected(message);
    }

    public NotificationEntity saveToDb(ProductReservedMessage message, NotificationType notificationType) {
        NotificationEntity entity = NotificationEntity.builder()
                .orderId(message.getOrderId())
                .status(notificationType)
                .build();
        NotificationEntity dbEntity = deliveryReservedRepository.save(entity);
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
