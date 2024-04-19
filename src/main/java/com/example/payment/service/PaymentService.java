package com.example.payment.service;

import com.example.payment.dto.OrderCreatedMessage;
import com.example.payment.dto.PaymentExecutedMessage;
import com.example.payment.dto.PaymentRejectedMessage;
import com.example.payment.entity.PaymentEntity;
import com.example.payment.entity.PaymentStatus;
import com.example.payment.kafka.KafkaProducerService;
import com.example.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
//    private final OrderCreatedMapper orderCreatedMapper;
    private final KafkaProducerService kafkaProducerService;

    public void process(OrderCreatedMessage message) {
        PaymentEntity paymentEntity = saveToDb(message);
        boolean isPaymentSuccess = executePayment(message.getOrderId());
        sendPaymentResult(message, paymentEntity, isPaymentSuccess);
    }

    private void sendPaymentResult(OrderCreatedMessage message, PaymentEntity paymentEntity, boolean isPaymentSuccess) {
        if (isPaymentSuccess) {
            log.info("Order successfully paid: {}", message);
            PaymentExecutedMessage paymentExecutedMessage = PaymentExecutedMessage.builder()
                    .orderId(message.getOrderId())
                    .orderDescription(message.getOrderDescription())
                    .productId(message.getProductId())
                    .deliveryAddress(message.getDeliveryAddress())
                    .paymentId(paymentEntity.getId())
                    .build();
            kafkaProducerService.sendSucceededPayment(paymentExecutedMessage);
        } else {
            log.warn("Order NOT paid: {}", message);
            PaymentRejectedMessage paymentRejectedMessage = PaymentRejectedMessage.builder()
                    .orderId(message.getOrderId())
                    .paymentId(paymentEntity.getId())
                    .errorCode("Order id dividable by 5")
                    .build();
            kafkaProducerService.sendRejectedPayment(paymentRejectedMessage);
        }
    }

    public PaymentEntity saveToDb(OrderCreatedMessage message) {
        PaymentEntity entity = new PaymentEntity();
        entity.setStatus(PaymentStatus.PENDING);
        entity.setOrderId(message.getOrderId());
        PaymentEntity dbEntity = paymentRepository.save(entity);
        return dbEntity;
    }

    /**
     *
     * @param orderId - from Order service
     * @return return false for all orderId divisible by 5.
     * Example: 5 - false, 10 - false, 2 - true
     */
    public boolean executePayment(Long orderId) {
        return (orderId%5L) != 0;
    }
}
