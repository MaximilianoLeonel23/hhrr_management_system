package com.maximiliano.backend.dto.task;

import com.maximiliano.backend.model.Status;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record TaskResponseDTO(
        Long id,
        String title,
        String description,
        Long assignedTo,
        Long project,
        LocalDate dueDate,
        Status status,
        LocalDateTime createdAt,
        LocalDateTime updateAt
) {
}
