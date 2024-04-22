package com.example.payment.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class DeliveryServiceTest {
    DeliveryService paymentService = new DeliveryService(null, null);

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 8})
    void reserveProduct_whenOrderIdNotDivisibleBy5plus4_thenReturnTrue(long orderId) {
        boolean result = paymentService.deliverProduct(orderId);
        Assertions.assertTrue(result);
    }

    @ParameterizedTest
    @ValueSource(ints = {7, 12, 17})
    void reserveProduct_whenOrderIdDivisibleBy5plus4_thenReturnFalse(long orderId) {
        boolean result = paymentService.deliverProduct(orderId);
        Assertions.assertFalse(result);
    }
}