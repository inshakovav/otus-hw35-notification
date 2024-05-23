package com.example.notification.service;

import com.example.notification.dto.PaymentExecutedMessage;
import com.example.notification.entity.NotificationEntity;
import com.example.notification.entity.NotificationType;
import com.example.notification.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public void paymentExecuted(PaymentExecutedMessage message) {
        NotificationEntity entity = NotificationEntity.builder()
                .type(NotificationType.PAYMENT_EXECUTED)
                .clientId(message.getClientId())
                .orderId(message.getOrderId())
                .orderPrice(message.getOrderPrice())
                .paymentId(message.getPaymentId())
                .build();
        notificationRepository.save(entity);
    }
}
