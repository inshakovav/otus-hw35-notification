package com.example.payment.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class PaymentExecutedMessage {
    private Long orderId;
    private String orderDescription;
    private Long productId;
    private String deliveryAddress;
    private Long paymentId;
}
