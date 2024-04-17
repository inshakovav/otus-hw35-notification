package com.example.payment.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PaymentServiceTest {
    PaymentService paymentService = new PaymentService();

    @Test
    void testExecutePayment_whenOrderIdNotDivisibleBy5_thenReturnTrue() {

        boolean result = paymentService.executePayment(Long.valueOf(1));
        Assertions.assertEquals(true, result);
    }

    @Test
    void testExecutePayment_whenOrderIdDivisibleBy5_thenReturnFalse() {

        boolean result = paymentService.executePayment(Long.valueOf(15));
        Assertions.assertEquals(false, result);
    }
}