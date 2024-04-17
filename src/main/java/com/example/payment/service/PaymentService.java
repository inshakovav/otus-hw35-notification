package com.example.payment.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    /**
     *
     * @param orderId - from Order service
     * @return return false for all orderId divisible by 5.
     * Example: 5 - false, 10 - false, 2 - true
     */
    public boolean executePayment(Long orderId) {
        return (orderId%5L) != 0;
    }
}
