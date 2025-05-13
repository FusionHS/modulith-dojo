package com.fusionhs.modulithdojo.pizza.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "pizza_order_stats",
    uniqueConstraints = @UniqueConstraint(columnNames = {"pizza_id", "order_date"}))
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PizzaOrderStats {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pizza_order_stats_seq")
    @SequenceGenerator(name = "pizza_order_stats_seq", sequenceName = "pizza_order_stats_seq")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pizza_id", nullable = false)
    private Pizza pizza;

    @Column(name = "order_date", nullable = false)
    private LocalDate orderDate;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;
} 