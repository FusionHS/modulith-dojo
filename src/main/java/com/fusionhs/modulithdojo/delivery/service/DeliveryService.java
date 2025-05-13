package com.fusionhs.modulithdojo.delivery.service;

import com.fusionhs.modulithdojo.common.enums.DeliveryStatus;
import com.fusionhs.modulithdojo.delivery.DeliveryApi;
import com.fusionhs.modulithdojo.delivery.dto.DeliveryDto;
import com.fusionhs.modulithdojo.delivery.mapper.DeliveryMapper;
import com.fusionhs.modulithdojo.delivery.model.Delivery;
import com.fusionhs.modulithdojo.delivery.repository.DeliveryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class DeliveryService implements DeliveryApi {
    private final DeliveryRepository deliveryRepository;
    private final DeliveryMapper deliveryMapper;

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
    public DeliveryDto createDelivery(DeliveryDto deliveryDto) {
        Delivery delivery = deliveryMapper.toEntity(deliveryDto);
        delivery.setOrderTime(LocalDateTime.now());
        delivery.setStatus(DeliveryStatus.ORDERED);
        return deliveryMapper.toDto(deliveryRepository.save(delivery));
    }

    @Override
    public DeliveryDto updateDelivery(Long id, DeliveryDto deliveryDto) {
        Delivery delivery = deliveryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Delivery not found with id: " + id));
        
        deliveryMapper.updateEntity(deliveryDto, delivery);
        return deliveryMapper.toDto(deliveryRepository.save(delivery));
    }

    @Override
    public DeliveryDto updateDeliveryStatus(Long id, DeliveryStatus status) {
        Delivery delivery = deliveryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Delivery not found with id: " + id));
        
        delivery.setStatus(status);
        if (status == DeliveryStatus.DELIVERED) {
            delivery.setDeliveryDate(LocalDateTime.now());
        }
        
        return deliveryMapper.toDto(deliveryRepository.save(delivery));
    }

    @Override
    public void deleteDelivery(Long id) {
        if (!deliveryRepository.existsById(id)) {
            throw new EntityNotFoundException("Delivery not found with id: " + id);
        }
        deliveryRepository.deleteById(id);
    }
} 