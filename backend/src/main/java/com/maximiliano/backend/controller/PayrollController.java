package com.maximiliano.backend.controller;

import com.maximiliano.backend.dto.payroll.PayrollDetailsResponseDTO;
import com.maximiliano.backend.dto.payroll.PayrollRequestDTO;
import com.maximiliano.backend.dto.payroll.PayrollResponseDTO;
import com.maximiliano.backend.service.PayrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/payrolls")
public class PayrollController {

    @Autowired
    private PayrollService payrollService;

    @GetMapping
    public ResponseEntity<List<PayrollResponseDTO>> getAllPayrolls() {
        List<PayrollResponseDTO> payrolls = payrollService.getAllPayrolls();
        return ResponseEntity.ok(payrolls);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PayrollDetailsResponseDTO> getPayrollByID(
            @PathVariable Long id
    ) {
        PayrollDetailsResponseDTO payroll = payrollService.getPayrollByID(id);
        return ResponseEntity.ok(payroll);
    }

    @PostMapping
    public ResponseEntity<PayrollResponseDTO> createNewPayroll(
            @RequestBody PayrollRequestDTO payrollRequestDTO
    ) {
        PayrollResponseDTO payroll = payrollService.createNewPayroll(payrollRequestDTO);
        URI uri = UriComponentsBuilder.fromPath("/api/payrolls/{id}").buildAndExpand(payroll.id()).toUri();
        return ResponseEntity.created(uri).body(payroll);
    }

}
