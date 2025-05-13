package com.fusionhs.modulithdojo.delivery.dto;

import com.fusionhs.modulithdojo.common.enums.DeliveryStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class DeliveryDto {
    private Long id;
    private String deliveryAddress;
    private String customerName;
    private String customerPhone;
    private LocalDateTime orderTime;
    private LocalDateTime deliveryTime;
    private Long chefId;
    private Long deliveryPersonId;
    private DeliveryStatus status;
    private List<DeliveryItemDto> items;
} 