package com.fusionhs.modulithdojo.pizza.mapper;

import com.fusionhs.modulithdojo.common.dto.pizza.PizzaDto;
import com.fusionhs.modulithdojo.pizza.model.Pizza;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PizzaMapper {
    PizzaDto toDto(Pizza pizza);
    List<PizzaDto> toDtoList(List<Pizza> pizzas);

    @Mapping(target = "id", ignore = true)
    Pizza toEntity(PizzaDto pizzaDto);

    Pizza updateEntity(PizzaDto pizzaDto, @MappingTarget Pizza pizza);
} 