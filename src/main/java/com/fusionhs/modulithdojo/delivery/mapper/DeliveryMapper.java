package com.fusionhs.modulithdojo.delivery.mapper;

import com.fusionhs.modulithdojo.common.dto.DeliveryDto;
import com.fusionhs.modulithdojo.delivery.model.Delivery;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = {DeliveryItemMapper.class})
public interface DeliveryMapper {
    DeliveryDto toDto(Delivery delivery);
    
    List<DeliveryDto> toDtoList(List<Delivery> deliveries);
    
    Delivery toEntity(DeliveryDto deliveryDto);
    
    void updateEntity(DeliveryDto deliveryDto, @MappingTarget Delivery delivery);
} 