package com.example.notification.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class DeliveryExecutedMessage {
    private Long orderId;
    private Long deliveryId;
}
