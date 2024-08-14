package com.maximiliano.backend.dto.payroll;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Optional;

public record PayrollRequestDTO(
        @NotNull(message = "Employee is required.")
        Long employee,
        @NotNull(message = "Period start is required.")
        LocalDate periodStart,
        Optional<LocalDate> periodEnd,
        @NotNull(message = "Gross pay is required.")
        Double grossPay,
        @NotNull(message = "Deductions are required.")
        Double deductions,
        @NotNull(message = "Net pay is required.")
        Double netPay
) {
}
