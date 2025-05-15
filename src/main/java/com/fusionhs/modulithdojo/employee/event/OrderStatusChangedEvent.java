package com.fusionhs.modulithdojo.employee.event;

import com.fusionhs.modulithdojo.common.dto.delivery.DeliveryStatus;
import lombok.Value;
import org.springframework.modulith.events.Externalized;

@Value
@Externalized("order.status.changed")
public class OrderStatusChangedEvent {
    Long orderId;
    DeliveryStatus newStatus;
    Long chefId;
    Long deliveryPersonId;
} 