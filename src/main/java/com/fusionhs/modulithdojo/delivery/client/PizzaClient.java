package com.fusionhs.modulithdojo.delivery.client;

import com.fusionhs.modulithdojo.common.dto.pizza.PizzaDto;
import com.fusionhs.modulithdojo.common.dto.pizza.PizzaSize;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@FeignClient(name = "pizza-service", url = "${pizza-service.url}")
public interface PizzaClient extends PizzaApi {

    @Override
    @GetMapping("/api/pizzas")
    List<PizzaDto> getAllPizzas();

    @Override
    @GetMapping("/api/pizzas/{id}")
    PizzaDto getPizzaById(@PathVariable("id") Long id);

    @Override
    @GetMapping("/api/pizzas/size/{size}")
    List<PizzaDto> getPizzasBySize(@PathVariable("size") PizzaSize size);

    @Override
    @GetMapping("/api/pizzas/search")
    List<PizzaDto> searchPizzasByName(@RequestParam("name") String name);

    @Override
    @PostMapping("/api/pizzas")
    PizzaDto createPizza(@RequestBody PizzaDto pizza);

    @Override
    @PutMapping("/api/pizzas/{id}")
    PizzaDto updatePizza(@PathVariable("id") Long id, @RequestBody PizzaDto pizza);

    @Override
    @DeleteMapping("/api/pizzas/{id}")
    void deletePizza(@PathVariable("id") Long id);

    @Override
    @PostMapping("/api/pizzas/{id}/increment-stats")
    void incrementPizzaOrderStats(@PathVariable("id") Long pizzaId, @RequestParam("quantity") int quantity);
} 