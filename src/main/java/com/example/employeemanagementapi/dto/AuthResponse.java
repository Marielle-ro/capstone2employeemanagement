package com.example.employeemanagementapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor       // ✔ required by Swagger
public class AuthResponse {
    private String token;
}
