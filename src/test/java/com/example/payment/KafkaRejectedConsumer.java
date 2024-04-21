//package com.example.payment;
//
//import com.example.payment.dto.WarehouseReservationRejectedMessage;
//import lombok.Getter;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Component;
//
//import java.util.concurrent.CountDownLatch;
//
//@Component
//@Slf4j
//@Getter
//public class KafkaRejectedConsumer {
//
//    private CountDownLatch latch = new CountDownLatch(1);
//    private WarehouseReservationRejectedMessage rejectedMessage;
//
//    @KafkaListener(topics = "${delivery.kafka.warehouse-product-reservation-rejected-topic}", groupId = "${delivery.kafka.message-group-name}")
//    public void receiveRejected(WarehouseReservationRejectedMessage message) {
//        log.info("received Rejected payload='{}'", message.toString());
//        rejectedMessage = message;
//        latch.countDown();
//    }
//
//    public void resetLatch() {
//        latch = new CountDownLatch(1);
//    }
//}
