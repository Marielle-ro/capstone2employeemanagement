package com.example.employeemanagementapi.config;

import com.example.employeemanagementapi.Entity.Role;
import com.example.employeemanagementapi.Entity.User;
import com.example.employeemanagementapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Seeds a default administrator account so that protected endpoints
 * can be exercised immediately after cloning the project.
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private static final String DEFAULT_ADMIN_EMAIL = "admin@employeemgr.com";
    private static final String DEFAULT_ADMIN_USERNAME = "admin";
    private static final String DEFAULT_ADMIN_PASSWORD = "Admin@123";

    @Override
    public void run(String... args) {
        userRepository.findByEmail(DEFAULT_ADMIN_EMAIL).ifPresentOrElse(
                user -> log.debug("Default admin already exists with email {}", DEFAULT_ADMIN_EMAIL),
                () -> {
                    User admin = User.builder()
                            .email(DEFAULT_ADMIN_EMAIL)
                            .userName(DEFAULT_ADMIN_USERNAME)
                            .password(passwordEncoder.encode(DEFAULT_ADMIN_PASSWORD))
                            .role(Role.ROLE_ADMIN)
                            .build();
                    userRepository.save(admin);
                    log.info("Created default admin account. email={} password={}", DEFAULT_ADMIN_EMAIL, DEFAULT_ADMIN_PASSWORD);
                }
        );
    }
}

