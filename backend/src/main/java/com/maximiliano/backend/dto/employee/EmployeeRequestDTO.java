package com.maximiliano.backend.dto.employee;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.Optional;

public record EmployeeRequestDTO(
        @NotBlank(message = "First name is required.")
        String firstname,
        @NotBlank(message = "Last name is required.")
        String lastname,
        @NotBlank(message = "Email is required.")
        @Email(message = "Invalid email format.")
        String email,
        Optional<String> department,
        Optional<String> role,
        Optional<LocalDate> dateOfHire,
        Optional<Double> salary
) {
}
