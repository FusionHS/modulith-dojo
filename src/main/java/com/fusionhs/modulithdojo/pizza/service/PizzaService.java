package com.fusionhs.modulithdojo.pizza.service;

import com.fusionhs.modulithdojo.common.dto.PizzaDto;
import com.fusionhs.modulithdojo.pizza.PizzaApi;
import com.fusionhs.modulithdojo.pizza.mapper.PizzaMapper;
import com.fusionhs.modulithdojo.pizza.model.Pizza;
import com.fusionhs.modulithdojo.pizza.repository.PizzaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PizzaService implements PizzaApi {
    private final PizzaRepository pizzaRepository;
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
} 