package com.fusionhs.modulithdojo.common.dto.delivery;

public record DeliveryItemDto(
        Long id,
        Long pizzaId,
        Integer quantity
) {
}