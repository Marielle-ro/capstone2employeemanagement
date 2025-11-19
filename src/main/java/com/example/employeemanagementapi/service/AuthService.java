package com.example.employeemanagementapi.service;

import com.example.employeemanagementapi.Entity.Role;
import com.example.employeemanagementapi.Entity.User;
import com.example.employeemanagementapi.Exception.BadRequestException;
import com.example.employeemanagementapi.dto.LoginRequest;
import com.example.employeemanagementapi.dto.RegisterRequest;
import com.example.employeemanagementapi.dto.AuthResponse;
import com.example.employeemanagementapi.repository.UserRepository;
import com.example.employeemanagementapi.config.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public String register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new BadRequestException("Email already in use");
        }

        if (userRepository.existsByUserName(request.getUsername())) {
            throw new BadRequestException("Username already in use");
        }

        Role roleToAssign = request.getRole() != null ? request.getRole() : Role.ROLE_USER;

        User user = User.builder()
                .userName(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(roleToAssign)
                .build();

        userRepository.save(user);

        return "User registered successfully!";
    }

    public AuthResponse login(LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        String token = jwtUtil.generateToken(request.getEmail());

        return new AuthResponse("Bearer " + token);
    }
}
