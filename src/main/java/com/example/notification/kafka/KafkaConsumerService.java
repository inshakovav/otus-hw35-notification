package com.example.notification.kafka;

import com.example.notification.dto.ProductReservedMessage;
import com.example.notification.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaConsumerService {

    private final DeliveryService deliveryService;
    @KafkaListener(topics = "${delivery.kafka.warehouse-product-reserved-topic}", groupId = "${delivery.kafka.message-group-name}")
    public void receivePaymentExecutedMessage(ProductReservedMessage message) {
        try {
            deliveryService.process(message);
        } catch (Exception e) {
            log.warn("Kafka unknown error Warehouse reservation processing: ", message);
        }

    }
}
