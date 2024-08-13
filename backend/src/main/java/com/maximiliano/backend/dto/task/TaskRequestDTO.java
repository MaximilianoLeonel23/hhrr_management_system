package com.maximiliano.backend.dto.task;

import com.maximiliano.backend.model.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Optional;

public record TaskRequestDTO(
        @NotBlank(message = "Title is required.")
        String title,
        Optional<String> description,
        @NotNull(message = "Assigned to an employee with its id is required.")
        Long assignedTo,
        @NotNull(message = "Project id is required")
        Long project,
        Optional<LocalDate> dueDate,
        Optional<Status> status
) {
}
