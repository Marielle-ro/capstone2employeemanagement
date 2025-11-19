package com.example.employeemanagementapi.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "employees")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Employee entity storing employee information")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema
    private Long id;

    @NotBlank(message = "Name is required")
    @Schema(description = "employee name")
    private String name;

    @NotBlank(message = "Position is required")
    @Schema(description = "Job title")
    private String position;

    @NotBlank(message = "Department is required")
    @Schema(description = "Department name")
    private String department;

    @NotNull(message = "Hire date is required")
    @PastOrPresent(message = "curent date")
    @Schema(description = "Date when employee was hired")
    private LocalDate hireDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    @JsonIgnore
    @Schema(description = "User who created the employee record")
    private User createdBy;
}
