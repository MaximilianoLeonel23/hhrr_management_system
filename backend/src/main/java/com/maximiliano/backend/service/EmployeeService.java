package com.maximiliano.backend.service;

import com.maximiliano.backend.dto.employee.EmployeeDetailsResponseDTO;
import com.maximiliano.backend.dto.employee.EmployeeRequestDTO;
import com.maximiliano.backend.dto.employee.EmployeeResponseDTO;
import com.maximiliano.backend.dto.employee.EmployeeUpdateRequestDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {


    public List<EmployeeResponseDTO> getAllEmployees() {
        return null;
    }

    public EmployeeDetailsResponseDTO getEmployeeByID(Long id) {
        return null;
    }

    public EmployeeResponseDTO createNewEmployee(EmployeeRequestDTO employeeRequestDTO) {
        return null;
    }

    public EmployeeResponseDTO updateEmployee(Long id, EmployeeUpdateRequestDTO employeeUpdateRequestDTO) {
        return null;
    }

    public void deleteEmployee(Long id) {
    }
}
