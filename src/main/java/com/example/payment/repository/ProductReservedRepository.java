package com.example.payment.repository;

import com.example.payment.entity.ProductReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductReservedRepository extends JpaRepository<ProductReservationEntity, Long> {
    Optional<ProductReservationEntity> findFirstByOrderByIdDesc();
    Optional<ProductReservationEntity> findFirstByOrderId(Long orderId);
}
