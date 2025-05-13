package com.fusionhs.modulithdojo.employee;

import com.fusionhs.modulithdojo.common.dto.EmployeeDto;
import com.fusionhs.modulithdojo.employee.model.Employee;

import java.util.List;

public interface EmployeeApi {
    List<EmployeeDto> getAllEmployees();
    EmployeeDto getEmployeeById(Long id);
    List<EmployeeDto> getEmployeesByDepartment(Employee.Department department);
    List<EmployeeDto> searchEmployees(String query);
    EmployeeDto createEmployee(EmployeeDto employee);
    EmployeeDto updateEmployee(Long id, EmployeeDto employee);
    void deleteEmployee(Long id);
} 