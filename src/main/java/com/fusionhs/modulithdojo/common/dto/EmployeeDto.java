package com.fusionhs.modulithdojo.common.dto;

import com.fusionhs.modulithdojo.common.enums.EmployeeDepartment;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class EmployeeDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String position;
    private BigDecimal salary;
    private LocalDate hireDate;
    private EmployeeDepartment department;
} 