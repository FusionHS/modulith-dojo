package com.fusionhs.modulithdojo.employee.event;

import com.fusionhs.modulithdojo.common.dto.employee.TaskStatus;
import com.fusionhs.modulithdojo.delivery.OrderStatusChangedEvent;
import com.fusionhs.modulithdojo.employee.service.EmployeeInternalApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class EmployeeEventHandler {
    private final EmployeeInternalApi employeeService;

    @ApplicationModuleListener
    public void on(OrderStatusChangedEvent event) {
        if (event.getChefId() != null) {
            updateEmployeeStatus(event.getChefId(), event);
        }
        if (event.getDeliveryPersonId() != null) {
            updateEmployeeStatus(event.getDeliveryPersonId(), event);
        }
    }

    private void updateEmployeeStatus(Long employeeId, OrderStatusChangedEvent event) {
        TaskStatus newStatus = determineNewStatus(employeeId, event);

        if (newStatus != null) {
            log.info("Updating employee {} status to {}", employeeId, newStatus);

            employeeService.updateEmployeeTaskStatus(employeeId, newStatus);

        }
    }

    private TaskStatus determineNewStatus(Long employeeId, OrderStatusChangedEvent event) {
        return switch (event.getNewStatus()) {
            case ORDERED, CANCELLED -> TaskStatus.WAITING;
            case PREPARING -> {
                if (employeeId.equals(event.getChefId())) {
                    yield TaskStatus.COOKING;
                } else {
                    yield null;
                }
            }
            case READY_FOR_DELIVERY -> {
                if (employeeId.equals(event.getChefId())) {
                    yield TaskStatus.WAITING;
                } else if (employeeId.equals(event.getDeliveryPersonId())) {
                    yield TaskStatus.WAITING;
                } else {
                    yield null;
                }
            }
            case IN_TRANSIT -> {
                if (employeeId.equals(event.getDeliveryPersonId())) {
                    yield TaskStatus.ON_ROUTE;
                } else {
                    yield null;
                }
            }
            case DELIVERED -> {
                if (employeeId.equals(event.getDeliveryPersonId())) {
                    yield TaskStatus.RETURNING;
                } else {
                    yield null;
                }
            }
        };
    }
} 