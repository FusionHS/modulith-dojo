package com.fusionhs.modulithdojo.employee.api;

import com.fusionhs.modulithdojo.common.dto.EmployeeDto;
import com.fusionhs.modulithdojo.employee.EmployeeApi;
import com.fusionhs.modulithdojo.employee.model.Employee;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeApi employeeService;

    @GetMapping
    public List<EmployeeDto> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public EmployeeDto getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }

    @GetMapping("/department/{department}")
    public List<EmployeeDto> getEmployeesByDepartment(@PathVariable Employee.Department department) {
        return employeeService.getEmployeesByDepartment(department);
    }

    @GetMapping("/search")
    public List<EmployeeDto> searchEmployees(@RequestParam String query) {
        return employeeService.searchEmployees(query);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeDto createEmployee(@Valid @RequestBody EmployeeDto employee) {
        return employeeService.createEmployee(employee);
    }

    @PutMapping("/{id}")
    public EmployeeDto updateEmployee(@PathVariable Long id, @Valid @RequestBody EmployeeDto employee) {
        return employeeService.updateEmployee(id, employee);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }
} 