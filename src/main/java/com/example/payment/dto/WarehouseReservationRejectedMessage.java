package com.example.payment.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class WarehouseReservationRejectedMessage {
    private Long orderId;
    private Long reservationId;
    private String errorCode;
}
