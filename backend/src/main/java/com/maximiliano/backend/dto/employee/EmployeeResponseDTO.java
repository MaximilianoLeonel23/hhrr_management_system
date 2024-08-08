package com.maximiliano.backend.dto.employee;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record EmployeeResponseDTO(
        Long id,
        String firstname,
        String lastname,
        String email,
        String department,
        String role,
        LocalDate dateOfHire,
        Double salary,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
