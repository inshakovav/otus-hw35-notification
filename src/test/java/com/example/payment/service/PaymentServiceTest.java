package com.example.payment.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PaymentServiceTest {
    WarehouseService paymentService = new WarehouseService(null, null);

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 7, 8})
    void reserveProduct_whenOrderIdNotDivisibleBy5plus4_thenReturnTrue(long orderId) {
        boolean result = paymentService.reserveProduct(orderId);
        Assertions.assertTrue(result);
    }

    @ParameterizedTest
    @ValueSource(ints = {6, 11, 16})
    void reserveProduct_whenOrderIdDivisibleBy5plus4_thenReturnFalse(long orderId) {
        boolean result = paymentService.reserveProduct(orderId);
        Assertions.assertFalse(result);
    }
}