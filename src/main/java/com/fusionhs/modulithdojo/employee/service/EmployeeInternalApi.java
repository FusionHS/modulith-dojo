package com.fusionhs.modulithdojo.employee.service;

import com.fusionhs.modulithdojo.common.dto.employee.TaskStatus;


public interface EmployeeInternalApi {
    void updateEmployeeTaskStatus(Long employeeId, TaskStatus newStatus);

}