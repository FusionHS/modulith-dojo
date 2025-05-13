package com.fusionhs.modulithdojo.common.dto;

import com.fusionhs.modulithdojo.common.enums.PizzaSize;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Data
public class PizzaDto {
    private Long id;
    private String name;
    private String description;
    private Set<String> toppings;
    private BigDecimal price;
    private PizzaSize size;
} 