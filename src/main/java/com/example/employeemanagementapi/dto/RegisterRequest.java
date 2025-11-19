package com.example.employeemanagementapi.dto;

import com.example.employeemanagementapi.Entity.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Payload used to register a new user")
public class RegisterRequest {

    @NotBlank(message = "Username is required")
    @Schema(example = "jane.doe")
    private String username;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    @Schema(example = "jane.doe@example.com")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters")
    @Schema(example = "Str0ngPass!")
    private String password;

    @Schema(description = "Optional role. Defaults to ROLE_USER.", example = "ROLE_USER")
    private Role role;
}
