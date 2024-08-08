package com.maximiliano.backend.dto.project;

import java.time.LocalDate;
import java.util.Optional;

public record ProjectUpdateRequestDTO(
        Optional<String> name,
        Optional<String> description,
        Optional<LocalDate> startDate,
        Optional<LocalDate> endDate
) {
}
