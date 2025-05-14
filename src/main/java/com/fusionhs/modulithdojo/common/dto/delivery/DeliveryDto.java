package com.fusionhs.modulithdojo.common.dto.delivery;

import java.time.LocalDateTime;
import java.util.List;

public record DeliveryDto(
    Long id,
    String deliveryAddress,
    String customerName,
    String customerPhone,
    LocalDateTime orderTime,
    LocalDateTime deliveryTime,
    Long chefId,
    Long deliveryPersonId,
    DeliveryStatus status,
    List<DeliveryItemDto> items
) {} 