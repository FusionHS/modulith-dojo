package com.fusionhs.modulithdojo.common.dto;

import lombok.Data;

@Data
public class DeliveryItemDto {
    private Long id;
    private Long pizzaId;
    private Integer quantity;
    private String pizzaName;
} 