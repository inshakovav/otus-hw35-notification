package com.example.notification.service;

import com.example.notification.dto.PaymentExecutedMessage;
import com.example.notification.dto.PaymentRejectedMessage;
import com.example.notification.entity.NotificationEntity;
import com.example.notification.entity.NotificationType;
import com.example.notification.repository.NotificationRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

//@SpringBootTest
@Slf4j
@ActiveProfiles("test")
class ApplicationTests {

    @Autowired
    NotificationRepository notificationRepository;
//
//    @Autowired
//    private KafkaSucceededConsumer kafkaSucceededConsumer;
//
//    @Autowired
//    private KafkaRejectedConsumer kafkaRejectedConsumer;
//
    @Autowired
    private KafkaPaymentProducer kafkaPaymentProducer;
//
    @Test
    @Disabled
    void paymentSucceededTest() throws InterruptedException {
        // setup
        PaymentExecutedMessage paymentExecutedMessage = PaymentExecutedMessage.builder()
                .accountId(1L)
                .orderId(3L)
                .orderPrice(new BigDecimal(2.1))
//                .paymentId(123L)
                .build();


        // act
        kafkaPaymentProducer.sendPaymentExecuted(paymentExecutedMessage);

        // verify
        sleep(3000);
        Optional<NotificationEntity> lastNotificationOptional = notificationRepository.findFirstByOrderByIdDesc();
        if (lastNotificationOptional.isEmpty()) {
            fail("Notification doesn't saved");
        }
        NotificationEntity lastNotification = lastNotificationOptional.get();
        assertEquals(NotificationType.PAYMENT_EXECUTED, lastNotification.getType());
        assertEquals(1L, lastNotification.getClientId());
        assertEquals(3L, lastNotification.getOrderId());
//        assertEquals(123L, lastNotification.getPaymentId());

        BigDecimal orderPrice = lastNotification.getOrderPrice();
        BigDecimal orderPriceRounded = orderPrice.setScale(2, RoundingMode.DOWN);
        BigDecimal expectedOrderPrice = new BigDecimal(2.1).setScale(2, RoundingMode.DOWN);
        assertEquals(expectedOrderPrice, orderPriceRounded);
    }

    @Test
    @Disabled
    void paymentRejectedTest() throws InterruptedException {
        // setup
        PaymentRejectedMessage paymentRejectedMessage = PaymentRejectedMessage.builder()
                .accountId(1L)
                .orderId(2L)
                .orderPrice(new BigDecimal(2.1))
//                .paymentId(123L)
                .errorCode("Insufficient funds in the account")
                .build();


        // act
        kafkaPaymentProducer.sendPaymentRejected(paymentRejectedMessage);

        // verify
        sleep(3000);
        Optional<NotificationEntity> lastNotificationOptional = notificationRepository.findFirstByOrderByIdDesc();
        if (lastNotificationOptional.isEmpty()) {
            fail("Notification doesn't saved");
        }
        NotificationEntity lastNotification = lastNotificationOptional.get();
        assertEquals(NotificationType.PAYMENT_REJECTED, lastNotification.getType());
        assertEquals(1L, lastNotification.getClientId());
        assertEquals(2L, lastNotification.getOrderId());
//        assertEquals(123L, lastNotification.getPaymentId());

        BigDecimal orderPrice = lastNotification.getOrderPrice();
        BigDecimal orderPriceRounded = orderPrice.setScale(2, RoundingMode.DOWN);
        BigDecimal expectedOrderPrice = new BigDecimal(2.1).setScale(2, RoundingMode.DOWN);
        assertEquals(expectedOrderPrice, orderPriceRounded);
    }

    @Test
    @Disabled
    void testDB() {
        Optional<NotificationEntity> firstByOrderByIdDesc = notificationRepository.findFirstByOrderByIdDesc();
        if (firstByOrderByIdDesc.isPresent()) {
            NotificationEntity entity = firstByOrderByIdDesc.get();
            log.info("Last by id: {}", entity);
        }
    }
}
