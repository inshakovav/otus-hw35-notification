package com.example.payment.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class PaymentExecutedMessage {
    private Long orderId;
    private String orderDescription;
    private Long productId;
    private String deliveryAddress;
    private Long paymentId;
}
