package com.fusionhs.modulithdojo.pizza.repository;

import com.fusionhs.modulithdojo.pizza.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PizzaRepository extends JpaRepository<Pizza, Long> {
    List<Pizza> findBySize(Pizza.Size size);
    List<Pizza> findByNameContainingIgnoreCase(String name);
} 