package com.maximiliano.backend.dto.employee;

import java.time.LocalDate;
import java.util.Optional;

public record EmployeeUpdateRequestDTO(
        Optional<String> firstname,
        Optional<String> lastname,
        Optional<String> email,
        Optional<String> department,
        Optional<String> role,
        Optional<LocalDate> dateOfHired,
        Optional<Double> salary
) {
}
