package com.fusionhs.modulithdojo.delivery.mapper;

import com.fusionhs.modulithdojo.common.dto.delivery.DeliveryItemDto;
import com.fusionhs.modulithdojo.delivery.model.DeliveryItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface DeliveryItemMapper {
    DeliveryItemDto toDto(DeliveryItem item);
    
    List<DeliveryItemDto> toDtoList(List<DeliveryItem> items);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "delivery", ignore = true)
    DeliveryItem toEntity(DeliveryItemDto itemDto);

    DeliveryItem updateEntity(@MappingTarget DeliveryItem item, DeliveryItemDto itemDto);
} 