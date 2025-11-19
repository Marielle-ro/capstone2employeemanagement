package com.example.employeemanagementapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "Payload used for user login")
public class LoginRequest {

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    @Schema(example = "jane.doe@gmail.com")
    private String email;

    @NotBlank(message = "Password is required")
    @Schema(example = "Str0ngPass!")
    private String password;
}
