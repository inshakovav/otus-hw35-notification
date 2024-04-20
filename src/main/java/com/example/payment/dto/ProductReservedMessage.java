package com.example.payment.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ProductReservedMessage {
    private Long orderId;
    private String orderDescription;
    private String deliveryAddress;
    private Long reservationId;
}
