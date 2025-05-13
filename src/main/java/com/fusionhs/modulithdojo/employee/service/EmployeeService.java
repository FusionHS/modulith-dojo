package com.fusionhs.modulithdojo.employee.service;

import com.fusionhs.modulithdojo.common.dto.EmployeeDto;
import com.fusionhs.modulithdojo.common.enums.TaskStatus;
import com.fusionhs.modulithdojo.employee.EmployeeApi;
import com.fusionhs.modulithdojo.employee.EmployeeTaskChangedEvent;
import com.fusionhs.modulithdojo.employee.mapper.EmployeeMapper;
import com.fusionhs.modulithdojo.employee.model.Employee;
import com.fusionhs.modulithdojo.employee.repository.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeService implements EmployeeApi, EmployeeInternalApi {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final ApplicationEventPublisher eventPublisher;

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
        employee = employeeRepository.save(employee);

        eventPublisher.publishEvent(new EmployeeTaskChangedEvent(
                employee.getId(),
                employee.getTaskStatus()
        ));

        return employeeMapper.toDto(employee);
    }

    @Override
    public EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with id: " + id));

        TaskStatus oldStatus = employee.getTaskStatus();
        employeeMapper.updateEntity(employeeDto, employee);
        employee = employeeRepository.save(employee);

        if (oldStatus != employee.getTaskStatus()) {
            eventPublisher.publishEvent(new EmployeeTaskChangedEvent(
                    employee.getId(),
                    employee.getTaskStatus()
            ));
        }

        return employeeMapper.toDto(employee);
    }


    @Override
    public void deleteEmployee(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new EntityNotFoundException("Employee not found with id: " + id);
        }
        employeeRepository.deleteById(id);
    }

    @Override
    public void updateEmployeeTaskStatus(Long employeeId, TaskStatus newStatus) {
        employeeRepository.findById(employeeId).ifPresent(employee -> {
            if (employee.getTaskStatus() != newStatus) {
                log.info("Updating employee {} task status from {} to {}",
                        employeeId, employee.getTaskStatus(), newStatus);
                employee.setTaskStatus(newStatus);
                employeeRepository.save(employee);
            }
        });
    }

} 