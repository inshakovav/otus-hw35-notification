package com.example.payment.kafka;

import com.example.payment.dto.OrderCreatedDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaConsumerService {

    @KafkaListener(topics = "hw30.order.created", groupId = "my-group")
    public void receiveMessage3(OrderCreatedDto message) {
        // Process the received message
        log.info("Received DTO message: {} from topic: {}", message, "my-topic2");
    }
}
