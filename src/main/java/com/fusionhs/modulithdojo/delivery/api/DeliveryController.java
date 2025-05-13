package com.fusionhs.modulithdojo.delivery.api;

import com.fusionhs.modulithdojo.common.dto.DeliveryDto;
import com.fusionhs.modulithdojo.common.enums.DeliveryStatus;
import com.fusionhs.modulithdojo.delivery.DeliveryApi;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/deliveries")
@RequiredArgsConstructor
public class DeliveryController {
    private final DeliveryApi deliveryService;

    @GetMapping
    public List<DeliveryDto> getAllDeliveries() {
        return deliveryService.getAllDeliveries();
    }

    @GetMapping("/{id}")
    public DeliveryDto getDeliveryById(@PathVariable Long id) {
        return deliveryService.getDeliveryById(id);
    }

    @GetMapping("/status/{status}")
    public List<DeliveryDto> getDeliveriesByStatus(@PathVariable DeliveryStatus status) {
        return deliveryService.getDeliveriesByStatus(status);
    }

    @GetMapping("/date-range")
    public List<DeliveryDto> getDeliveriesByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        return deliveryService.getDeliveriesByDateRange(start, end);
    }

    @GetMapping("/employee/{employeeId}")
    public List<DeliveryDto> getDeliveriesByEmployee(@PathVariable Long employeeId) {
        return deliveryService.getDeliveriesByEmployee(employeeId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DeliveryDto createDelivery(@Valid @RequestBody DeliveryDto delivery) {
        return deliveryService.createDelivery(delivery);
    }

    @PutMapping("/{id}")
    public DeliveryDto updateDelivery(@PathVariable Long id, @Valid @RequestBody DeliveryDto delivery) {
        return deliveryService.updateDelivery(id, delivery);
    }

    @PutMapping("/{id}/status")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateOrderStatus(
            @PathVariable Long id,
            @RequestParam DeliveryStatus status) {
        deliveryService.updateDeliveryStatusCommand(id, status);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDelivery(@PathVariable Long id) {
        deliveryService.deleteDelivery(id);
    }
} 