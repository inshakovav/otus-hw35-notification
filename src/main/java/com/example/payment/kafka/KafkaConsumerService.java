package com.example.payment.kafka;

import com.example.payment.dto.OrderCreatedMessage;
import com.example.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaConsumerService {

    private final PaymentService paymentService;

    @KafkaListener(topics = "hw30.order.created", groupId = "my-group")
    public void receiveOrderCreatedMessage(OrderCreatedMessage message) {
        boolean isPaymentSuccess = paymentService.executePayment(message.getOrderId());
        if (isPaymentSuccess) {
            log.info("Order successfully paid: {}", message);
        } else {
            log.warn("Order NOT paid: {}", message);
        }
    }
}
