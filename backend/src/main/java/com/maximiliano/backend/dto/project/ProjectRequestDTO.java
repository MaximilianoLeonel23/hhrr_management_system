package com.maximiliano.backend.dto.project;

import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.Optional;

public record ProjectRequestDTO(
        String name,
        Optional<String> description,
        LocalDate startDate,
        Optional<Local> endDate
) {
}
