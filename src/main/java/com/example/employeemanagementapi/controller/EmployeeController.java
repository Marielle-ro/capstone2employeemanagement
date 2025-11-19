package com.example.employeemanagementapi.controller;

import com.example.employeemanagementapi.Entity.Employee;
import com.example.employeemanagementapi.Entity.User;
import com.example.employeemanagementapi.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
@Tag(name = "Employees API", description = "Endpoints for managing employee records")
@SecurityRequirement(name = "bearerAuth") // 🔐 Require token for entire controller
public class EmployeeController {

    private final EmployeeService service;

    @Operation(
            summary = "Get all employees",
            description = "Returns a list of all employees in the database"
    )
    @GetMapping
    public List<Employee> getAll() {
        return service.getAll();
    }

    @Operation(
            summary = "Get a single employee",
            description = "Retrieve an employee using their ID"
    )
    @GetMapping("/{id}")
    public Employee getOne(@PathVariable Long id) {
        return service.getById(id);
    }

    @Operation(
            summary = "Create a new employee",
            description = "Adds a new employee to the system"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Employee create(@Valid @RequestBody Employee employee,
                           @AuthenticationPrincipal User creator) {
        return service.create(employee, creator);
    }

    @Operation(
            summary = "Update an existing employee",
            description = "Update an employee using their ID"
    )
    @PutMapping("/{id}")
    public Employee update(@PathVariable Long id, @Valid @RequestBody Employee employee) {
        return service.update(id, employee);
    }

    @Operation(
            summary = "Delete an employee",
            description = "Delete an employee using their ID"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "Employee deleted";
    }
}
