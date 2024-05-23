package com.example.notification.repository;

import com.example.notification.entity.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeliveryReservedRepository extends JpaRepository<NotificationEntity, Long> {
    Optional<NotificationEntity> findFirstByOrderByIdDesc();
    Optional<NotificationEntity> findFirstByOrderId(Long orderId);
}
