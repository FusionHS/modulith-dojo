package com.fusionhs.modulithdojo.common.dto.pizza;

import java.math.BigDecimal;
import java.util.Set;

public record PizzaDto(
    Long id,
    String name,
    String description,
    Set<String> toppings,
    BigDecimal price,
    PizzaSize size
) {} 