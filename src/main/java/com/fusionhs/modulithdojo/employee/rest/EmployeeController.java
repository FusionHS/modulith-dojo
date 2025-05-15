package com.fusionhs.modulithdojo.employee.rest;

import com.fusionhs.modulithdojo.common.dto.employee.EmployeeDto;
import com.fusionhs.modulithdojo.employee.EmployeeApi;
import com.fusionhs.modulithdojo.employee.model.Employee;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
@Tag(name = "Employee", description = "Employee management endpoints")
public class EmployeeController {
    private final EmployeeApi employeeService;

    @GetMapping
    @Operation(summary = "Get all employees", description = "Retrieves a list of all employees")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved all employees")
    public List<EmployeeDto> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get employee by ID", description = "Retrieves a specific employee by their ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Successfully retrieved the employee"),
        @ApiResponse(responseCode = "404", description = "Employee not found", content = @Content)
    })
    public EmployeeDto getEmployeeById(
            @Parameter(description = "ID of the employee to retrieve") 
            @PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }

    @GetMapping("/department/{department}")
    @Operation(summary = "Get employees by department", description = "Retrieves all employees in a specific department")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved employees by department")
    public List<EmployeeDto> getEmployeesByDepartment(
            @Parameter(description = "Department to filter employees by (IT, HR, FINANCE, MARKETING, OPERATIONS, SALES)") 
            @PathVariable Employee.Department department) {
        return employeeService.getEmployeesByDepartment(department);
    }

    @GetMapping("/search")
    @Operation(summary = "Search employees", description = "Searches for employees by name, email, or other attributes")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved matching employees")
    public List<EmployeeDto> searchEmployees(
            @Parameter(description = "Search query string") 
            @RequestParam String query) {
        return employeeService.searchEmployees(query);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create new employee", description = "Creates a new employee in the system")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Employee successfully created"),
        @ApiResponse(responseCode = "400", description = "Invalid employee data provided", 
                    content = @Content(schema = @Schema(implementation = EmployeeDto.class)))
    })
    public EmployeeDto createEmployee(
            @Parameter(description = "Employee details", required = true) 
            @Valid @RequestBody EmployeeDto employee) {
        return employeeService.createEmployee(employee);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update employee", description = "Updates an existing employee")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Employee successfully updated"),
        @ApiResponse(responseCode = "404", description = "Employee not found"),
        @ApiResponse(responseCode = "400", description = "Invalid employee data provided")
    })
    public EmployeeDto updateEmployee(
            @Parameter(description = "ID of the employee to update") 
            @PathVariable Long id,
            @Parameter(description = "Updated employee details", required = true) 
            @Valid @RequestBody EmployeeDto employee) {
        return employeeService.updateEmployee(id, employee);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete employee", description = "Deletes an existing employee")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Employee successfully deleted"),
        @ApiResponse(responseCode = "404", description = "Employee not found")
    })
    public void deleteEmployee(
            @Parameter(description = "ID of the employee to delete") 
            @PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }
} 