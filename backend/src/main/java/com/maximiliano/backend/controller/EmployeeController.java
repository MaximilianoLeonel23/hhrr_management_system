package com.maximiliano.backend.controller;

import com.maximiliano.backend.dto.employee.EmployeeDetailsResponseDTO;
import com.maximiliano.backend.dto.employee.EmployeeRequestDTO;
import com.maximiliano.backend.dto.employee.EmployeeResponseDTO;
import com.maximiliano.backend.dto.employee.EmployeeUpdateRequestDTO;
import com.maximiliano.backend.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PreAuthorize("hasRole('ADMIN') or (hasRole('MANAGER'))")
    @GetMapping
    public ResponseEntity<List<EmployeeResponseDTO>> getAllEmployees() {
        List<EmployeeResponseDTO> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    @PreAuthorize("hasRole('ADMIN') or (hasRole('MANAGER')) or ((hasRole('EMPLOYEE') and #id == principal.id))")
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDetailsResponseDTO> getEmployeeByID(@PathVariable Long id) {
        EmployeeDetailsResponseDTO employee = employeeService.getEmployeeByID(id);
        return ResponseEntity.ok(employee);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<EmployeeResponseDTO> createNewEmployee(
            @Valid @RequestBody EmployeeRequestDTO employeeRequestDTO
    ) {
        EmployeeResponseDTO employee = employeeService.createNewEmployee(employeeRequestDTO);
        URI uri = UriComponentsBuilder.fromPath("/api/employees/{id}").buildAndExpand(employee.id()).toUri();
        return ResponseEntity.created(uri).body(employee);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> updateEmployee(
            @PathVariable Long id,
            @Valid @RequestBody EmployeeUpdateRequestDTO employeeUpdateRequestDTO
    ) {
        EmployeeResponseDTO employee = employeeService.updateEmployee(id, employeeUpdateRequestDTO);
        return ResponseEntity.ok(employee);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(
            @PathVariable Long id
    ) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
}
