package com.maximiliano.backend.dto.task;

import com.maximiliano.backend.dto.employee.EmployeeResponseDTO;
import com.maximiliano.backend.dto.project.ProjectResponseDTO;
import com.maximiliano.backend.model.Status;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record TaskDetailsResponseDTO(
        Long id,
        String title,
        String description,
        EmployeeResponseDTO assignedTo,
        ProjectResponseDTO project,
        LocalDate dueDate,
        Status status,
        LocalDateTime createdAt,
        LocalDateTime updateAt
) {
}
