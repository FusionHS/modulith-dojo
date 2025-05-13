package com.fusionhs.modulithdojo.employee;

import com.fusionhs.modulithdojo.common.enums.TaskStatus;
import lombok.Value;
import org.springframework.modulith.events.Externalized;

@Value
@Externalized("employee.task.changed")
public class EmployeeTaskChangedEvent {
    Long employeeId;
    TaskStatus newStatus;
} 