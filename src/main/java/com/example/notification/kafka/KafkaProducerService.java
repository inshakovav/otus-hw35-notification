//package com.example.notification.kafka;
//
//import com.example.notification.dto.DeliveryExecutedMessage;
//import com.example.notification.dto.DeliveryRejectedMessage;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class KafkaProducerService {
//    @Value("${delivery.kafka.delivery-executed-topic}")
//    private String deliveryExecutedTopic;
//
//    @Value("${delivery.kafka.delivery-rejected-topic}")
//    private String deliveryRejectedTopic;
//
//    private final KafkaTemplate<String, Object> kafkaTemplate;
//
//    public void sendDeliveryExecuted(DeliveryExecutedMessage message) {
//        kafkaTemplate.send(deliveryExecutedTopic, message);
//    }
//
//    public void sendDeliveryRejected(DeliveryRejectedMessage message) {
//        kafkaTemplate.send(deliveryRejectedTopic, message);
//    }
//}
