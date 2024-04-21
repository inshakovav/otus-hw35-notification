//package com.example.payment;
//
//import com.example.payment.dto.ProductReservedMessage;
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
//public class KafkaSucceededConsumer {
//
//    private CountDownLatch latch = new CountDownLatch(1);
//    private ProductReservedMessage executedMessage;
//
//    @KafkaListener(topics = "${delivery.kafka.warehouse-product-reserved-topic}", groupId = "${delivery.kafka.message-group-name}")
//    public void receiveSucceeded(ProductReservedMessage message) {
//        log.info("received Executed payload='{}'", message.toString());
//        executedMessage = message;
//        latch.countDown();
//    }
//
//    public void resetLatch() {
//        latch = new CountDownLatch(1);
//    }
//}
