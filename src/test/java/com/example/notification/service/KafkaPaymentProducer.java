package com.example.notification.service;

import com.example.notification.dto.PaymentExecutedMessage;
import com.example.notification.dto.PaymentRejectedMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaPaymentProducer {
    @Value("${app.kafka.payment-executed-topic}")
    private String paymentExecutedTopic;

    @Value("${app.kafka.payment-rejected-topic}")
    private String paymentRejectedTopic;

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendPaymentExecuted(PaymentExecutedMessage message) {
        kafkaTemplate.send(paymentExecutedTopic, message);
    }

    public void sendPaymentRejected(PaymentRejectedMessage message) {
        kafkaTemplate.send(paymentRejectedTopic, message);
    }
}
