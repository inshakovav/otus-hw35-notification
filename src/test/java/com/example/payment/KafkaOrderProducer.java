package com.example.payment;

import com.example.payment.dto.PaymentExecutedMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaOrderProducer {
    @Value("${warehouse.kafka.payment-succeeded-topic}")
    private String paymentSucceededTopic;

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendPaymentExecuted(PaymentExecutedMessage message) {
        kafkaTemplate.send(paymentSucceededTopic, message);
    }
}
