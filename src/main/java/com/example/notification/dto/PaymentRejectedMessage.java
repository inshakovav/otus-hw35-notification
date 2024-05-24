package com.example.notification.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class PaymentRejectedMessage {
    private Long accountId;
    private Long orderId;
    private BigDecimal orderPrice;
//    private Long paymentId;
    private String errorCode;
}
