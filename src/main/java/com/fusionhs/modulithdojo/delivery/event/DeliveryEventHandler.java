package com.fusionhs.modulithdojo.delivery.event;

import com.fusionhs.modulithdojo.delivery.OrderStatusChangedEvent;
import com.fusionhs.modulithdojo.delivery.service.DeliveryInternalApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DeliveryEventHandler {
    private final DeliveryInternalApi deliveryService;

    @ApplicationModuleListener
    public void on(OrderStatusChangedEvent event) {
        log.info("Updating delivery {} status to {}", event.getOrderId(), event.getNewStatus());
        deliveryService.updateDeliveryStatus(event.getOrderId(), event.getNewStatus());
    }
} 