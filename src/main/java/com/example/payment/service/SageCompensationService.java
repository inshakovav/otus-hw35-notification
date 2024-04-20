package com.example.payment.service;

import com.example.payment.dto.DeliveryRejectedMessage;
import com.example.payment.dto.WarehouseReservationRejectedMessage;
import com.example.payment.entity.ProductReservationEntity;
import com.example.payment.repository.ProductReservedRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SageCompensationService {

    private final ProductReservedRepository productReservedRepository;

    @Transactional
    public void executeWarehouseReject(WarehouseReservationRejectedMessage message) {
        ProductReservationEntity payment = findPaymentByOrderId(message.getOrderId());
        productReservedRepository.deleteById(payment.getId());
        log.info("Delete reservation. Warehouse reservation was rejected: {}", message);
    }

    @Transactional
    public void executeDeliveryReject(DeliveryRejectedMessage message) {
        ProductReservationEntity payment = findPaymentByOrderId(message.getOrderId());
        productReservedRepository.deleteById(payment.getId());
        log.info("Delete reservation. Delivery was rejected: {}", message);
    }

    private ProductReservationEntity findPaymentByOrderId(long orderId) {
        return productReservedRepository.findFirstByOrderId(orderId)
                .orElseThrow(() -> new NumberFormatException("Wrong product reservation rejection. Can't find product by order id" + orderId));
    }
}
