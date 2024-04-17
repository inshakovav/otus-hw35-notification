package com.example.payment.kafka;

import com.example.payment.dto.OrderCreatedMessage;
import com.example.payment.dto.PaymentExecutedMessage;
import com.example.payment.dto.PaymentRejectedMessage;
import com.example.payment.entity.PaymentEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendSucceededPayment(PaymentExecutedMessage message) {
        kafkaTemplate.send("hw30.payment.succeeded", message);
    }

    public void sendRejectedPayment(PaymentRejectedMessage message) {
        kafkaTemplate.send("hw30.payment.rejected", message);
    }
}
