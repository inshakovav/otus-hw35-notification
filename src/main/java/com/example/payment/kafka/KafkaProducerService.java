package com.example.payment.kafka;

import com.example.payment.dto.OrderCreatedMessage;
import com.example.payment.dto.PaymentExecutedMessage;
import com.example.payment.dto.PaymentRejectedMessage;
import com.example.payment.entity.PaymentEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {
    @Value("${payment.kafka.payment-succeeded-topic}")
    private String paymentSucceededTopic;

    @Value("${payment.kafka.payment-rejected-topic}")
    private String paymentRejectedTopic;

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendSucceededPayment(PaymentExecutedMessage message) {
        kafkaTemplate.send(paymentSucceededTopic, message);
    }

    public void sendRejectedPayment(PaymentRejectedMessage message) {
        kafkaTemplate.send(paymentRejectedTopic, message);
    }
}
