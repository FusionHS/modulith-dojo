package com.fusionhs.modulithdojo.delivery.mapper;

import com.fusionhs.modulithdojo.delivery.dto.DeliveryItemDto;
import com.fusionhs.modulithdojo.delivery.model.DeliveryItem;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DeliveryItemMapper {
    DeliveryItemDto toDto(DeliveryItem item);
    
    List<DeliveryItemDto> toDtoList(List<DeliveryItem> items);
    
    DeliveryItem toEntity(DeliveryItemDto itemDto);
    
    void updateEntity(DeliveryItemDto itemDto, @MappingTarget DeliveryItem item);
} 