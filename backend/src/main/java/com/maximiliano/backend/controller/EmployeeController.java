package com.maximiliano.backend.controller;

import com.maximiliano.backend.dto.employee.EmployeeDetailsResponseDTO;
import com.maximiliano.backend.dto.employee.EmployeeRequestDTO;
import com.maximiliano.backend.dto.employee.EmployeeResponseDTO;
import com.maximiliano.backend.dto.employee.EmployeeUpdateRequestDTO;
import com.maximiliano.backend.model.Employee;
import com.maximiliano.backend.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<EmployeeResponseDTO>> getAllEmployees() {
        List<EmployeeResponseDTO> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDetailsResponseDTO> getEmployeeByID(@PathVariable Long id) {
        EmployeeDetailsResponseDTO employee = employeeService.getEmployeeByID(id);
        return ResponseEntity.ok(employee);
    }

    @PostMapping
    public ResponseEntity<EmployeeResponseDTO> createNewEmployee(
            @RequestBody EmployeeRequestDTO employeeRequestDTO
    ) {
        EmployeeResponseDTO employee = employeeService.createNewEmployee(employeeRequestDTO);
        URI uri = UriComponentsBuilder.fromPath("/api/employees/{id}").buildAndExpand("1").toUri();
        return ResponseEntity.created(uri).body(employee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> updateEmployee(
            @PathVariable Long id,
            @RequestBody EmployeeUpdateRequestDTO employeeUpdateRequestDTO
    ) {
        EmployeeResponseDTO employee = employeeService.updateEmployee(id, employeeUpdateRequestDTO);
        return ResponseEntity.ok(employee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(
            @PathVariable Long id
    ) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
}
