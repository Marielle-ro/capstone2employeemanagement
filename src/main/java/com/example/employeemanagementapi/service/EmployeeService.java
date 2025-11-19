package com.example.employeemanagementapi.service;

import com.example.employeemanagementapi.Entity.Employee;
import com.example.employeemanagementapi.Entity.User;
import com.example.employeemanagementapi.Exception.BadRequestException;
import com.example.employeemanagementapi.Exception.ResourceNotFoundException;
import com.example.employeemanagementapi.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository repo;

    public List<Employee> getAll() {
        return repo.findAll();
    }

    public Employee getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
    }

    public Employee create(Employee employee, User creator) {
        if (creator == null) {
            throw new BadRequestException("Authenticated user is required to create employees");
        }
        employee.setId(null);
        employee.setCreatedBy(creator);
        return repo.save(employee);
    }

    public Employee update(Long id, Employee updatedEmployee) {
        Employee existing = getById(id);
        existing.setName(updatedEmployee.getName());
        existing.setDepartment(updatedEmployee.getDepartment());
        existing.setPosition(updatedEmployee.getPosition());
        existing.setHireDate(updatedEmployee.getHireDate());
        return repo.save(existing);
    }

    public void delete(Long id) {
        Employee existing = getById(id);
        repo.delete(existing);
    }
}
