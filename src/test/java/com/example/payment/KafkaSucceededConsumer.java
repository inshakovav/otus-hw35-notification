//package com.example.payment;
//
//import com.example.payment.dto.PaymentExecutedMessage;
//import com.example.payment.dto.PaymentRejectedMessage;
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
//    private PaymentExecutedMessage executedMessage;
//
//    @KafkaListener(topics = "${warehouse.kafka.payment-succeeded-topic}", groupId = "${warehouse.kafka.message-group-name}")
//    public void receiveSucceeded(PaymentExecutedMessage message) {
//        log.info("received Executed payload='{}'", message.toString());
//        executedMessage = message;
//        latch.countDown();
//    }
//
//    public void resetLatch() {
//        latch = new CountDownLatch(1);
//    }
//}
