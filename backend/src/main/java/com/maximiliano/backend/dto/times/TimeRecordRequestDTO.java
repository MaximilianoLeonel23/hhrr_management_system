package com.maximiliano.backend.dto.times;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record TimeRecordRequestDTO(
        @NotNull(message = "Employee ID is required.")
        Long employee,
        @NotNull(message = "Date is required.")
        LocalDate date,
        @NotNull(message = "Hours worked are required.")
        Double hoursWorked
) {
}
