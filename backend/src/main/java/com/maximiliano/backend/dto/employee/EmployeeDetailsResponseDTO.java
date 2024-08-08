package com.maximiliano.backend.dto.employee;

import com.maximiliano.backend.dto.project.ProjectResponseDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record EmployeeDetailsResponseDTO(
        Long id,
        String firstname,
        String lastname,
        String email,
        String department,
        String role,
        LocalDate dateOfHire,
        Double salary,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        List<ProjectResponseDTO> projects
) {
}
