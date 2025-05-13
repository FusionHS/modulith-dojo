package com.fusionhs.modulithdojo.employee.mapper;

import com.fusionhs.modulithdojo.common.dto.EmployeeDto;
import com.fusionhs.modulithdojo.employee.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    EmployeeDto toDto(Employee employee);
    List<EmployeeDto> toDtoList(List<Employee> employees);
    Employee toEntity(EmployeeDto employeeDto);
    void updateEntity(EmployeeDto employeeDto, @MappingTarget Employee employee);
} 