package com.example.notification.kafka;

import com.example.notification.dto.PaymentExecutedMessage;
//import com.example.notification.service.DeliveryService;
import com.example.notification.dto.PaymentRejectedMessage;
import com.example.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaConsumerService {

    private final NotificationService notificationService;

    @KafkaListener(topics = "${app.kafka.payment-executed-topic}", groupId = "${app.kafka.message-group-name}")
    public void receivePaymentExecutedMessage(PaymentExecutedMessage message) {
        try {
            log.info("Receive succeeded message: {}", message);
            notificationService.paymentExecuted(message);
        } catch (Exception e) {
            log.warn("Kafka unknown error Warehouse reservation processing: ", message);
        }
    }

    @KafkaListener(topics = "${app.kafka.payment-rejected-topic}", groupId = "${app.kafka.message-group-name}")
    public void receivePaymentRejectedMessage(PaymentRejectedMessage message) {
        try {
            log.info("Receive rejected message: {}", message);
            notificationService.paymentRejected(message);
        } catch (Exception e) {
            log.warn("Kafka unknown error Warehouse reservation processing: ", message);
        }
    }
}
