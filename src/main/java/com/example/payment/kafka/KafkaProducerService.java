package com.example.payment.kafka;

import com.example.payment.dto.OrderCreatedDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendOrder(OrderCreatedDto message) {
        kafkaTemplate.send("my-topic2", message);
    }
}
