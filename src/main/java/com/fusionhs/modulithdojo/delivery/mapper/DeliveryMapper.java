package com.fusionhs.modulithdojo.delivery.mapper;

import com.fusionhs.modulithdojo.common.dto.delivery.DeliveryDto;
import com.fusionhs.modulithdojo.delivery.model.Delivery;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = {DeliveryItemMapper.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface DeliveryMapper {

    DeliveryDto toDto(Delivery delivery);

    List<DeliveryDto> toDtoList(List<Delivery> deliveries);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "items", ignore = true)
    default Delivery toEntity(DeliveryDto deliveryDto, @Context DeliveryItemMapper itemMapper) {
        if (deliveryDto == null) {
            return null;
        }

        Delivery.DeliveryBuilder delivery = Delivery.builder();

        delivery.customerName(deliveryDto.customerName());
        delivery.customerPhone(deliveryDto.customerPhone());
        delivery.deliveryAddress(deliveryDto.deliveryAddress());
        delivery.orderTime(deliveryDto.orderTime());
        delivery.status(deliveryDto.status());
        delivery.chefId(deliveryDto.chefId());
        delivery.deliveryPersonId(deliveryDto.deliveryPersonId());

        var build = delivery.build();
        linkItems(build, deliveryDto, itemMapper);

        return build;
    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "items", ignore = true)
    Delivery updateEntity(@MappingTarget Delivery delivery, DeliveryDto deliveryDto, @Context DeliveryItemMapper itemMapper);

    @AfterMapping
    default void linkItems(@MappingTarget Delivery delivery, DeliveryDto deliveryDto, @Context DeliveryItemMapper itemMapper) {
        if (deliveryDto.items() != null) {
            delivery.setItems(deliveryDto.items().stream()
                    .map(itemDto -> {
                        var item = itemMapper.toEntity(itemDto);
                        item.setDelivery(delivery);
                        return item;
                    })
                    .toList());
        }
    }
} 