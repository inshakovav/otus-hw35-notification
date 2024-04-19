package com.example.payment.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRejectedMessage {
    private Long orderId;
    private Long paymentId;
    private String errorCode;
}
