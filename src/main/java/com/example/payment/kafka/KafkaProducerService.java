//package com.example.payment.kafka;
//
//import com.example.payment.dto.ProductReservedMessage;
//import com.example.payment.dto.WarehouseReservationRejectedMessage;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class KafkaProducerService {
//    @Value("${delivery.kafka.warehouse-product-reserved-topic}")
//    private String productReservedTopic;
//
//    @Value("${delivery.kafka.warehouse-product-reservation-rejected-topic}")
//    private String productReservationRejectedTopic;
//
//    private final KafkaTemplate<String, Object> kafkaTemplate;
//
//    public void sendProductReserved(ProductReservedMessage message) {
//        kafkaTemplate.send(productReservedTopic, message);
//    }
//
//    public void sendProductReservationRejected(WarehouseReservationRejectedMessage message) {
//        kafkaTemplate.send(productReservationRejectedTopic, message);
//    }
//}
