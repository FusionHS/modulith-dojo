package com.fusionhs.modulithdojo.delivery.service;

import com.fusionhs.modulithdojo.common.dto.delivery.DeliveryDto;
import com.fusionhs.modulithdojo.common.dto.delivery.DeliveryStatus;
import com.fusionhs.modulithdojo.delivery.DeliveryApi;
import com.fusionhs.modulithdojo.delivery.OrderStatusChangedEvent;
import com.fusionhs.modulithdojo.delivery.mapper.DeliveryItemMapper;
import com.fusionhs.modulithdojo.delivery.mapper.DeliveryMapper;
import com.fusionhs.modulithdojo.delivery.model.Delivery;
import com.fusionhs.modulithdojo.delivery.repository.DeliveryRepository;
import com.fusionhs.modulithdojo.pizza.PizzaApi;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class DeliveryService implements DeliveryApi, DeliveryInternalApi {
    private final DeliveryRepository deliveryRepository;
    private final DeliveryMapper deliveryMapper;
    private final DeliveryItemMapper deliveryItemMapper;
    private final ApplicationEventPublisher eventPublisher;
    private final PizzaApi pizzaApi;

    @Override
    public List<DeliveryDto> getAllDeliveries() {
        return deliveryMapper.toDtoList(deliveryRepository.findAll());
    }

    @Override
    public DeliveryDto getDeliveryById(Long id) {
        return deliveryMapper.toDto(deliveryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Delivery not found with id: " + id)));
    }

    @Override
    public List<DeliveryDto> getDeliveriesByStatus(DeliveryStatus status) {
        return deliveryMapper.toDtoList(deliveryRepository.findByStatus(status));
    }

    @Override
    public List<DeliveryDto> getDeliveriesByDateRange(LocalDateTime start, LocalDateTime end) {
        return deliveryMapper.toDtoList(deliveryRepository.findByDateRange(start, end));
    }

    @Override
    public List<DeliveryDto> getDeliveriesByEmployee(Long employeeId) {
        return deliveryMapper.toDtoList(deliveryRepository.findByEmployee(employeeId));
    }

    @Override
    @Transactional
    public DeliveryDto createDelivery(DeliveryDto deliveryDto) {
        Delivery delivery = deliveryMapper.toEntity(deliveryDto, deliveryItemMapper);
        delivery.setOrderTime(LocalDateTime.now());
        delivery.setStatus(DeliveryStatus.ORDERED);
        delivery = deliveryRepository.save(delivery);

        if (deliveryDto.items() != null) {
            deliveryDto.items().forEach(item -> {
                pizzaApi.incrementPizzaOrderStats(item.pizzaId(), item.quantity());
            });
        }

        eventPublisher.publishEvent(new OrderStatusChangedEvent(
                delivery.getId(),
                DeliveryStatus.ORDERED,
                delivery.getChefId(),
                delivery.getDeliveryPersonId()
        ));

        return deliveryMapper.toDto(delivery);
    }

    @Override
    public DeliveryDto updateDelivery(Long id, DeliveryDto deliveryDto) {
        Delivery delivery = deliveryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Delivery not found with id: " + id));

        DeliveryStatus oldStatus = delivery.getStatus();
        delivery = deliveryMapper.updateEntity(delivery, deliveryDto, deliveryItemMapper);
        delivery = deliveryRepository.save(delivery);

        if (oldStatus != delivery.getStatus()) {
            eventPublisher.publishEvent(new OrderStatusChangedEvent(
                    delivery.getId(),
                    delivery.getStatus(),
                    delivery.getChefId(),
                    delivery.getDeliveryPersonId()
            ));
        }

        return deliveryMapper.toDto(delivery);
    }

    @Override
    public void updateDeliveryStatusCommand(Long id, DeliveryStatus status) {
        Delivery delivery = deliveryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Delivery not found with id: " + id));

        eventPublisher.publishEvent(new OrderStatusChangedEvent(
                id,
                status,
                delivery.getChefId(),
                delivery.getDeliveryPersonId()
        ));
    }

    @Override
    public void deleteDelivery(Long id) {
        if (!deliveryRepository.existsById(id)) {
            throw new EntityNotFoundException("Delivery not found with id: " + id);
        }
        deliveryRepository.deleteById(id);
    }

    @Override
    public void updateDeliveryStatus(Long id, DeliveryStatus status) {
        deliveryRepository.findById(id).ifPresent(delivery -> {
            delivery.setStatus(status);
            if (status == DeliveryStatus.DELIVERED) {
                delivery.setDeliveryDate(LocalDateTime.now());
            }
            deliveryRepository.save(delivery);
        });
    }
} 