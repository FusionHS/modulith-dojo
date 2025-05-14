package com.fusionhs.modulithdojo.delivery;

import com.fusionhs.modulithdojo.common.dto.delivery.DeliveryDto;
import com.fusionhs.modulithdojo.common.dto.delivery.DeliveryStatus;

import java.time.LocalDateTime;
import java.util.List;

public interface DeliveryApi {
    List<DeliveryDto> getAllDeliveries();
    DeliveryDto getDeliveryById(Long id);
    List<DeliveryDto> getDeliveriesByStatus(DeliveryStatus status);
    List<DeliveryDto> getDeliveriesByDateRange(LocalDateTime start, LocalDateTime end);
    List<DeliveryDto> getDeliveriesByEmployee(Long employeeId);
    DeliveryDto createDelivery(DeliveryDto delivery);
    DeliveryDto updateDelivery(Long id, DeliveryDto delivery);

    void updateDeliveryStatusCommand(Long id, DeliveryStatus status);
    void deleteDelivery(Long id);
} 