package com.fusionhs.modulithdojo.delivery.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "delivery_item")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryItem {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "delivery_item_seq")
    @SequenceGenerator(name = "delivery_item_seq", sequenceName = "delivery_item_seq")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id", nullable = false)
    private Delivery delivery;

    @Column(name = "pizza_id", nullable = false)
    private Long pizzaId;

    @Column(nullable = false)
    private Integer quantity;
} 