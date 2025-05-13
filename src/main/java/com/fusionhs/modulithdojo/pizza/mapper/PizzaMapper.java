package com.fusionhs.modulithdojo.pizza.mapper;

import com.fusionhs.modulithdojo.common.dto.PizzaDto;
import com.fusionhs.modulithdojo.pizza.model.Pizza;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PizzaMapper {
    PizzaDto toDto(Pizza pizza);
    List<PizzaDto> toDtoList(List<Pizza> pizzas);
    Pizza toEntity(PizzaDto pizzaDto);
    void updateEntity(PizzaDto pizzaDto, @MappingTarget Pizza pizza);
} 