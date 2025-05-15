package com.fusionhs.modulithdojo.delivery.rest;

import com.fusionhs.modulithdojo.common.dto.delivery.DeliveryDto;
import com.fusionhs.modulithdojo.common.dto.delivery.DeliveryStatus;
import com.fusionhs.modulithdojo.delivery.DeliveryApi;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Delivery", description = "Delivery management endpoints")
public class DeliveryController {
    private final DeliveryApi deliveryService;

    @GetMapping
    @Operation(summary = "Get all deliveries", description = "Retrieves a list of all deliveries in the system")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved all deliveries")
    public List<DeliveryDto> getAllDeliveries() {
        return deliveryService.getAllDeliveries();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get delivery by ID", description = "Retrieves a specific delivery by its ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Successfully retrieved the delivery"),
        @ApiResponse(responseCode = "404", description = "Delivery not found", content = @Content)
    })
    public DeliveryDto getDeliveryById(
            @Parameter(description = "ID of the delivery to retrieve") 
            @PathVariable Long id) {
        return deliveryService.getDeliveryById(id);
    }

    @GetMapping("/status/{status}")
    @Operation(summary = "Get deliveries by status", description = "Retrieves all deliveries with a specific status")
    public List<DeliveryDto> getDeliveriesByStatus(
            @Parameter(description = "Status to filter deliveries by") 
            @PathVariable DeliveryStatus status) {
        return deliveryService.getDeliveriesByStatus(status);
    }

    @GetMapping("/date-range")
    @Operation(summary = "Get deliveries by date range", 
              description = "Retrieves all deliveries within a specific date range")
    public List<DeliveryDto> getDeliveriesByDateRange(
            @Parameter(description = "Start date and time (ISO format)") 
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @Parameter(description = "End date and time (ISO format)") 
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        return deliveryService.getDeliveriesByDateRange(start, end);
    }

    @GetMapping("/employee/{employeeId}")
    @Operation(summary = "Get deliveries by employee", 
              description = "Retrieves all deliveries assigned to a specific employee")
    public List<DeliveryDto> getDeliveriesByEmployee(
            @Parameter(description = "ID of the employee") 
            @PathVariable Long employeeId) {
        return deliveryService.getDeliveriesByEmployee(employeeId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create new delivery", description = "Creates a new delivery in the system")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Delivery successfully created"),
        @ApiResponse(responseCode = "400", description = "Invalid delivery data provided", 
                    content = @Content(schema = @Schema(implementation = DeliveryDto.class)))
    })
    public DeliveryDto createDelivery(
            @Parameter(description = "Delivery details", required = true) 
            @Valid @RequestBody DeliveryDto delivery) {
        return deliveryService.createDelivery(delivery);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update delivery", description = "Updates an existing delivery")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Delivery successfully updated"),
        @ApiResponse(responseCode = "404", description = "Delivery not found"),
        @ApiResponse(responseCode = "400", description = "Invalid delivery data provided")
    })
    public DeliveryDto updateDelivery(
            @Parameter(description = "ID of the delivery to update") 
            @PathVariable Long id,
            @Parameter(description = "Updated delivery details", required = true) 
            @Valid @RequestBody DeliveryDto delivery) {
        return deliveryService.updateDelivery(id, delivery);
    }

    @PutMapping("/{id}/status")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Operation(summary = "Update delivery status", 
              description = "Updates the status of an existing delivery")
    @ApiResponses({
        @ApiResponse(responseCode = "202", description = "Status update accepted"),
        @ApiResponse(responseCode = "404", description = "Delivery not found")
    })
    public void updateOrderStatus(
            @Parameter(description = "ID of the delivery") 
            @PathVariable Long id,
            @Parameter(description = "New delivery status", required = true) 
            @RequestParam DeliveryStatus status) {
        deliveryService.updateDeliveryStatusCommand(id, status);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete delivery", description = "Deletes an existing delivery")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Delivery successfully deleted"),
        @ApiResponse(responseCode = "404", description = "Delivery not found")
    })
    public void deleteDelivery(
            @Parameter(description = "ID of the delivery to delete") 
            @PathVariable Long id) {
        deliveryService.deleteDelivery(id);
    }
} 