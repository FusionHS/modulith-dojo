package com.fusionhs.modulithdojo.delivery.repository;

import com.fusionhs.modulithdojo.common.dto.delivery.DeliveryStatus;
import com.fusionhs.modulithdojo.delivery.model.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
    List<Delivery> findByStatus(DeliveryStatus status);

    @Query("SELECT d FROM Delivery d WHERE d.orderTime BETWEEN :start AND :end")
    List<Delivery> findByDateRange(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

    @Query("SELECT d FROM Delivery d WHERE d.chefId = :employeeId OR d.deliveryPersonId = :employeeId")
    List<Delivery> findByEmployee(@Param("employeeId") Long employeeId);
} 