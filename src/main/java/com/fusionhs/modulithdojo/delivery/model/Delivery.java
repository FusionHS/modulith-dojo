package com.fusionhs.modulithdojo.delivery.model;

import com.fusionhs.modulithdojo.common.dto.delivery.DeliveryStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "delivery")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "delivery_seq")
    @SequenceGenerator(name = "delivery_seq", sequenceName = "delivery_seq")
    private Long id;

    @Column(name = "customer_name", nullable = false)
    private String customerName;

    @Column(name = "customer_phone", nullable = false)
    private String customerPhone;

    @Column(name = "delivery_address", nullable = false)
    private String deliveryAddress;

    @Column(name = "order_time", nullable = false)
    private LocalDateTime orderTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DeliveryStatus status;

    @Column(name = "delivery_date")
    private LocalDateTime deliveryDate;

    @Column(name = "chef_id")
    private Long chefId;

    @Column(name = "delivery_person_id")
    private Long deliveryPersonId;

    @OneToMany(mappedBy = "delivery", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<DeliveryItem> items = new ArrayList<>();

    public void addItem(DeliveryItem item) {
        items.add(item);
        item.setDelivery(this);
    }

    public void removeItem(DeliveryItem item) {
        items.remove(item);
        item.setDelivery(null);
    }

    public void setItems(List<DeliveryItem> items) {
        this.items.clear();
        if (items != null) {
            items.forEach(this::addItem);
        }
    }
} 