package com.fusionhs.modulithdojo.pizza.api;

import com.fusionhs.modulithdojo.common.dto.PizzaDto;
import com.fusionhs.modulithdojo.pizza.PizzaApi;
import com.fusionhs.modulithdojo.pizza.model.Pizza;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pizzas")
@RequiredArgsConstructor
public class PizzaController {
    private final PizzaApi pizzaService;

    @GetMapping
    public List<PizzaDto> getAllPizzas() {
        return pizzaService.getAllPizzas();
    }

    @GetMapping("/{id}")
    public PizzaDto getPizzaById(@PathVariable Long id) {
        return pizzaService.getPizzaById(id);
    }

    @GetMapping("/size/{size}")
    public List<PizzaDto> getPizzasBySize(@PathVariable Pizza.Size size) {
        return pizzaService.getPizzasBySize(size);
    }

    @GetMapping("/search")
    public List<PizzaDto> searchPizzas(@RequestParam String name) {
        return pizzaService.searchPizzasByName(name);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PizzaDto createPizza(@Valid @RequestBody PizzaDto pizza) {
        return pizzaService.createPizza(pizza);
    }

    @PutMapping("/{id}")
    public PizzaDto updatePizza(@PathVariable Long id, @Valid @RequestBody PizzaDto pizza) {
        return pizzaService.updatePizza(id, pizza);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePizza(@PathVariable Long id) {
        pizzaService.deletePizza(id);
    }
} 