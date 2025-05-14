package com.fusionhs.modulithdojo.delivery.service;

import com.fusionhs.modulithdojo.common.dto.delivery.DeliveryStatus;


public interface DeliveryInternalApi {

    void updateDeliveryStatus(Long id, DeliveryStatus status);
} 