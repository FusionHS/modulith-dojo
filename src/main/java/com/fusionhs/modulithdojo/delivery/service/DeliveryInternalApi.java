package com.fusionhs.modulithdojo.delivery.service;

import com.fusionhs.modulithdojo.common.enums.DeliveryStatus;


public interface DeliveryInternalApi {

    void updateDeliveryStatus(Long id, DeliveryStatus status);
} 