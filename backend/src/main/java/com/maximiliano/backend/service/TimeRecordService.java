package com.maximiliano.backend.service;

import com.maximiliano.backend.dto.employee.EmployeeDetailsResponseDTO;
import com.maximiliano.backend.dto.employee.EmployeeResponseDTO;
import com.maximiliano.backend.dto.project.ProjectResponseDTO;
import com.maximiliano.backend.dto.times.TimeRecordDetailsResponseDTO;
import com.maximiliano.backend.dto.times.TimeRecordRequestDTO;
import com.maximiliano.backend.dto.times.TimeRecordResponseDTO;
import com.maximiliano.backend.exception.employee.DuplicateEmployeeEmailException;
import com.maximiliano.backend.exception.employee.EmployeeNotFoundException;
import com.maximiliano.backend.exception.employee.NoEmployeesFoundException;
import com.maximiliano.backend.exception.times.NoTimeRecordsFoundException;
import com.maximiliano.backend.exception.times.TimeRecordNotFoundException;
import com.maximiliano.backend.model.Employee;
import com.maximiliano.backend.model.TimeRecord;
import com.maximiliano.backend.repository.EmployeeRepository;
import com.maximiliano.backend.repository.TimeRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TimeRecordService {
    @Autowired
    private TimeRecordRepository timeRecordRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<TimeRecordResponseDTO> getAllTimeRecords() {
        List<TimeRecord> timeRecords = timeRecordRepository.findAll();
        if (!timeRecords.isEmpty()) {
            return timeRecords.stream().map(t -> new TimeRecordResponseDTO(
                    t.getId(),
                    t.getEmployee().getId(),
                    t.getDate(),
                    t.getHoursWorked(),
                    t.getCreatedAt(),
                    t.getUpdatedAt()
            )).toList();
        } else {
            throw new NoTimeRecordsFoundException("No time records found.");
        }
    }


    public TimeRecordDetailsResponseDTO getTimeRecordByID(Long id) {
        Optional<TimeRecord> timeRecordFound = timeRecordRepository.findById(id);
        if (timeRecordFound.isPresent()) {
            TimeRecord timeRecord = timeRecordFound.get();
            Employee employee = timeRecord.getEmployee();
            EmployeeResponseDTO employeeDTO = new EmployeeResponseDTO(
                    employee.getId(),
                    employee.getFirstName(),
                    employee.getLastName(),
                    employee.getEmail(),
                    employee.getDepartment(),
                    employee.getRole(),
                    employee.getDateOfHire(),
                    employee.getSalary(),
                    employee.getCreatedAt(),
                    employee.getUpdatedAt()
            );
            return new TimeRecordDetailsResponseDTO(
                    timeRecord.getId(),
                    employeeDTO,
                    timeRecord.getDate(),
                    timeRecord.getHoursWorked(),
                    timeRecord.getCreatedAt(),
                    timeRecord.getUpdatedAt()
            );
        } else {
            throw new TimeRecordNotFoundException("Time record with ID " + id + " not found.");
        }
    }

    public TimeRecordResponseDTO createNewTimeRecord(TimeRecordRequestDTO timeRecordRequestDTO) {

        Optional<Employee> employee = employeeRepository.findById(timeRecordRequestDTO.employee());
        if (employee.isPresent()) {
            TimeRecord newTimeRecord = new TimeRecord(timeRecordRequestDTO, employee.get());
            TimeRecord timeRecord = timeRecordRepository.save(newTimeRecord);
            return new TimeRecordResponseDTO(
                    timeRecord.getId(),
                    timeRecord.getEmployee().getId(),
                    timeRecord.getDate(),
                    timeRecord.getHoursWorked(),
                    timeRecord.getCreatedAt(),
                    timeRecord.getUpdatedAt()
            );
        } else {
            throw new TimeRecordNotFoundException("Time record with employee id " + timeRecordRequestDTO.employee() + " not found.");
        }
    }

}
