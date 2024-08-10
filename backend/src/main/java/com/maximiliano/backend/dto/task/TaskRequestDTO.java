package com.maximiliano.backend.dto.task;

import com.maximiliano.backend.model.Status;

import java.time.LocalDate;
import java.util.Optional;

public record TaskRequestDTO(
        String title,
        Optional<String> description,
        Long assignedTo,
        Long project,
        Optional<LocalDate> dueDate,
        Optional<Status> status
) {
}
