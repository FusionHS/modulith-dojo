package com.fusionhs.modulithdojo.employee.mapper;

import com.fusionhs.modulithdojo.common.dto.employee.EmployeeDto;
import com.fusionhs.modulithdojo.employee.model.Employee;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface EmployeeMapper {
    EmployeeDto toDto(Employee employee);
    List<EmployeeDto> toDtoList(List<Employee> employees);

    @Mapping(target = "id", ignore = true)
    Employee toEntity(EmployeeDto employeeDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Employee updateEntity(EmployeeDto employeeDto, @MappingTarget Employee employee);
} 