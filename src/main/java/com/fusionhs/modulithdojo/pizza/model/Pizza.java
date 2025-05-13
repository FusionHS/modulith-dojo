package com.fusionhs.modulithdojo.pizza.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "pizza")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pizza {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pizza_seq")
    @SequenceGenerator(name = "pizza_seq", sequenceName = "pizza_seq")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @ElementCollection
    @CollectionTable(name = "pizza_topping", joinColumns = @JoinColumn(name = "pizza_id"))
    @Column(name = "topping")
    private Set<String> toppings = new HashSet<>();

    @Column(nullable = false)
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Size size;

    public enum Size {
        SMALL, MEDIUM, LARGE
    }
} 