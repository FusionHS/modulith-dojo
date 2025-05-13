package com.fusionhs.modulithdojo.employee.service;

import com.fusionhs.modulithdojo.common.dto.EmployeeDto;
import com.fusionhs.modulithdojo.employee.EmployeeApi;
import com.fusionhs.modulithdojo.employee.mapper.EmployeeMapper;
import com.fusionhs.modulithdojo.employee.model.Employee;
import com.fusionhs.modulithdojo.employee.repository.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeService implements EmployeeApi {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    @Override
    public List<EmployeeDto> getAllEmployees() {
        return employeeMapper.toDtoList(employeeRepository.findAll());
    }

    @Override
    public EmployeeDto getEmployeeById(Long id) {
        return employeeMapper.toDto(employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with id: " + id)));
    }

    @Override
    public List<EmployeeDto> getEmployeesByDepartment(Employee.Department department) {
        return employeeMapper.toDtoList(employeeRepository.findByDepartment(department));
    }

    @Override
    public List<EmployeeDto> searchEmployees(String query) {
        return employeeMapper.toDtoList(employeeRepository.searchByTerm(query));
    }

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = employeeMapper.toEntity(employeeDto);
        return employeeMapper.toDto(employeeRepository.save(employee));
    }

    @Override
    public EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with id: " + id));
        
        employeeMapper.updateEntity(employeeDto, employee);
        return employeeMapper.toDto(employeeRepository.save(employee));
    }

    @Override
    public void deleteEmployee(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new EntityNotFoundException("Employee not found with id: " + id);
        }
        employeeRepository.deleteById(id);
    }
} 