package com.example.notification.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class PaymentExecutedMessage {
    private Long clientId;
    private Long orderId;
    private BigDecimal orderPrice;
    private Long paymentId;
}
