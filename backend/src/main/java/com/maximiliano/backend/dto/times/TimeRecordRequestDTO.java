package com.maximiliano.backend.dto.times;

import java.time.LocalDate;

public record TimeRecordRequestDTO(
    Long employee,
    LocalDate date,
    Double hoursWorked
) {
}
