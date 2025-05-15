package com.fusionhs.modulithdojo.pizza.rest;

import com.fusionhs.modulithdojo.common.dto.pizza.PizzaDto;
import com.fusionhs.modulithdojo.pizza.PizzaApi;
import com.fusionhs.modulithdojo.pizza.model.Pizza;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pizzas")
@RequiredArgsConstructor
@Tag(name = "Pizza", description = "Pizza menu management endpoints")
public class PizzaController {
    private final PizzaApi pizzaService;

    @GetMapping
    @Operation(summary = "Get all pizzas", description = "Retrieves a list of all pizzas in the menu")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved all pizzas")
    public List<PizzaDto> getAllPizzas() {
        return pizzaService.getAllPizzas();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get pizza by ID", description = "Retrieves a specific pizza by its ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Successfully retrieved the pizza"),
        @ApiResponse(responseCode = "404", description = "Pizza not found", content = @Content)
    })
    public PizzaDto getPizzaById(
            @Parameter(description = "ID of the pizza to retrieve") 
            @PathVariable Long id) {
        return pizzaService.getPizzaById(id);
    }

    @GetMapping("/size/{size}")
    @Operation(summary = "Get pizzas by size", description = "Retrieves all pizzas of a specific size")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved pizzas by size")
    public List<PizzaDto> getPizzasBySize(
            @Parameter(description = "Size to filter pizzas by (SMALL, MEDIUM, LARGE)") 
            @PathVariable Pizza.Size size) {
        return pizzaService.getPizzasBySize(size);
    }

    @GetMapping("/search")
    @Operation(summary = "Search pizzas by name", description = "Searches for pizzas containing the given name")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved matching pizzas")
    public List<PizzaDto> searchPizzas(
            @Parameter(description = "Name to search for") 
            @RequestParam String name) {
        return pizzaService.searchPizzasByName(name);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create new pizza", description = "Creates a new pizza in the menu")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Pizza successfully created"),
        @ApiResponse(responseCode = "400", description = "Invalid pizza data provided", 
                    content = @Content(schema = @Schema(implementation = PizzaDto.class)))
    })
    public PizzaDto createPizza(
            @Parameter(description = "Pizza details", required = true) 
            @Valid @RequestBody PizzaDto pizza) {
        return pizzaService.createPizza(pizza);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update pizza", description = "Updates an existing pizza")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Pizza successfully updated"),
        @ApiResponse(responseCode = "404", description = "Pizza not found"),
        @ApiResponse(responseCode = "400", description = "Invalid pizza data provided")
    })
    public PizzaDto updatePizza(
            @Parameter(description = "ID of the pizza to update") 
            @PathVariable Long id,
            @Parameter(description = "Updated pizza details", required = true) 
            @Valid @RequestBody PizzaDto pizza) {
        return pizzaService.updatePizza(id, pizza);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete pizza", description = "Deletes an existing pizza")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Pizza successfully deleted"),
        @ApiResponse(responseCode = "404", description = "Pizza not found")
    })
    public void deletePizza(
            @Parameter(description = "ID of the pizza to delete") 
            @PathVariable Long id) {
        pizzaService.deletePizza(id);
    }
} 