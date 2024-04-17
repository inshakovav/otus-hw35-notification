package com.example.payment.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentRejectedMessage {
    private Long orderId;
    private Long paymentId;
    private String errorCode;
}
