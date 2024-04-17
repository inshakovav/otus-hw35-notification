package com.example.payment;

import com.example.payment.dto.OrderCreatedMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducer {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendOrder(OrderCreatedMessage message) {
        kafkaTemplate.send("hw30.order.created", message);
    }
}
