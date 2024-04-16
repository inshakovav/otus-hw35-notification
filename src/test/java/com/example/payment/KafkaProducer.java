package com.example.payment;

import com.example.payment.dto.OrderCreatedDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducer {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendOrder(OrderCreatedDto message) {
        kafkaTemplate.send("hw30.order.created", message);
    }
}
