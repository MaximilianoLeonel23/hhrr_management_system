package com.maximiliano.backend.dto.project;

import com.maximiliano.backend.dto.employee.EmployeeResponseDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record ProjectDetailsResponseDTO(
        Long id,
        String name,
        String description,
        LocalDate startDate,
        LocalDate endDate,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        List<EmployeeResponseDTO> employees
) {
}
