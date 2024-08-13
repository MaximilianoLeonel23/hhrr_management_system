package com.maximiliano.backend.dto.project;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.Optional;

public record ProjectRequestDTO(
        @NotBlank(message = "Name is required.")
        String name,
        Optional<String> description,
        @NotNull(message = "Start date is required.")
        LocalDate startDate,
        Optional<LocalDate> endDate
) {
}
