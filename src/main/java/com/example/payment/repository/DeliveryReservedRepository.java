package com.example.payment.repository;

import com.example.payment.entity.DeliveryReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeliveryReservedRepository extends JpaRepository<DeliveryReservationEntity, Long> {
    Optional<DeliveryReservationEntity> findFirstByOrderByIdDesc();
    Optional<DeliveryReservationEntity> findFirstByOrderId(Long orderId);
}
