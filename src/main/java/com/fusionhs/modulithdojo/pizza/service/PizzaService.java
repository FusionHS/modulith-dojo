package com.fusionhs.modulithdojo.pizza.service;

import com.fusionhs.modulithdojo.common.dto.pizza.PizzaDto;
import com.fusionhs.modulithdojo.pizza.PizzaApi;
import com.fusionhs.modulithdojo.pizza.mapper.PizzaMapper;
import com.fusionhs.modulithdojo.pizza.model.Pizza;
import com.fusionhs.modulithdojo.pizza.model.PizzaOrderStats;
import com.fusionhs.modulithdojo.pizza.repository.PizzaOrderStatsRepository;
import com.fusionhs.modulithdojo.pizza.repository.PizzaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class PizzaService implements PizzaApi {
    private final PizzaRepository pizzaRepository;
    private final PizzaOrderStatsRepository pizzaOrderStatsRepository;
    private final PizzaMapper pizzaMapper;

    @Override
    public List<PizzaDto> getAllPizzas() {
        return pizzaMapper.toDtoList(pizzaRepository.findAll());
    }

    @Override
    public PizzaDto getPizzaById(Long id) {
        return pizzaMapper.toDto(pizzaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pizza not found with id: " + id)));
    }

    @Override
    public List<PizzaDto> getPizzasBySize(Pizza.Size size) {
        return pizzaMapper.toDtoList(pizzaRepository.findBySize(size));
    }

    @Override
    public List<PizzaDto> searchPizzasByName(String name) {
        return pizzaMapper.toDtoList(pizzaRepository.findByNameContainingIgnoreCase(name));
    }

    @Override
    public PizzaDto createPizza(PizzaDto pizzaDto) {
        Pizza pizza = pizzaMapper.toEntity(pizzaDto);
        return pizzaMapper.toDto(pizzaRepository.save(pizza));
    }

    @Override
    public PizzaDto updatePizza(Long id, PizzaDto pizzaDto) {
        Pizza pizza = pizzaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pizza not found with id: " + id));
        
        pizzaMapper.updateEntity(pizzaDto, pizza);
        return pizzaMapper.toDto(pizzaRepository.save(pizza));
    }

    @Override
    public void deletePizza(Long id) {
        if (!pizzaRepository.existsById(id)) {
            throw new EntityNotFoundException("Pizza not found with id: " + id);
        }
        pizzaRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void incrementPizzaOrderStats(Long pizzaId, int quantity) {
        Pizza pizza = pizzaRepository.findById(pizzaId)
                .orElseThrow(() -> new EntityNotFoundException("Pizza not found with id: " + pizzaId));

        LocalDate today = LocalDate.now();

        PizzaOrderStats stats = pizzaOrderStatsRepository.findByPizzaAndDateForUpdate(pizza, today)
                .orElseGet(() -> PizzaOrderStats.builder()
                        .pizza(pizza)
                        .orderDate(today)
                        .quantity(0)
                        .build());

        stats.setQuantity(stats.getQuantity() + quantity);
        pizzaOrderStatsRepository.save(stats);
    }
} 