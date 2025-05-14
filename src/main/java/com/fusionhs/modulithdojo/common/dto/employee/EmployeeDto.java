package com.fusionhs.modulithdojo.common.dto.employee;

import java.math.BigDecimal;
import java.time.LocalDate;

public record EmployeeDto(
    Long id,
    String firstName,
    String lastName,
    String email,
    String position,
    BigDecimal salary,
    LocalDate hireDate,
    EmployeeDepartment department
) {} 