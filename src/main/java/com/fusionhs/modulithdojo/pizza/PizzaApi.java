package com.fusionhs.modulithdojo.pizza;

import com.fusionhs.modulithdojo.common.dto.pizza.PizzaDto;
import com.fusionhs.modulithdojo.pizza.model.Pizza;

import java.util.List;

public interface PizzaApi {
    List<PizzaDto> getAllPizzas();
    PizzaDto getPizzaById(Long id);
    List<PizzaDto> getPizzasBySize(Pizza.Size size);
    List<PizzaDto> searchPizzasByName(String name);
    PizzaDto createPizza(PizzaDto pizza);
    PizzaDto updatePizza(Long id, PizzaDto pizza);
    void deletePizza(Long id);
    void incrementPizzaOrderStats(Long pizzaId, int quantity);
} 