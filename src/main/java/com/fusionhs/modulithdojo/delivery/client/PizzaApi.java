package com.fusionhs.modulithdojo.delivery.client;

import com.fusionhs.modulithdojo.common.dto.pizza.PizzaDto;
import com.fusionhs.modulithdojo.common.dto.pizza.PizzaSize;

import java.util.List;

public interface PizzaApi {
    List<PizzaDto> getAllPizzas();
    PizzaDto getPizzaById(Long id);

    List<PizzaDto> getPizzasBySize(PizzaSize size);
    List<PizzaDto> searchPizzasByName(String name);
    PizzaDto createPizza(PizzaDto pizza);
    PizzaDto updatePizza(Long id, PizzaDto pizza);
    void deletePizza(Long id);
    void incrementPizzaOrderStats(Long pizzaId, int quantity);
} 