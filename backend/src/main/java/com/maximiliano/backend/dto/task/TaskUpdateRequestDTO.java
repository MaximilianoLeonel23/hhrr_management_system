package com.maximiliano.backend.dto.task;

import com.maximiliano.backend.model.Status;

import java.time.LocalDate;
import java.util.Optional;

public record TaskUpdateRequestDTO(
        Optional<String> title,
        Optional<String> description,
        Optional<Long> assignedTo,
        Optional<Long> project,
        Optional<LocalDate> dueDate,
        Optional<Status> status
) {
}
