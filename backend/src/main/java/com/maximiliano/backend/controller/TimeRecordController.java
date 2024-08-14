package com.maximiliano.backend.controller;


import com.maximiliano.backend.dto.times.TimeRecordDetailsResponseDTO;
import com.maximiliano.backend.dto.times.TimeRecordRequestDTO;
import com.maximiliano.backend.dto.times.TimeRecordResponseDTO;
import com.maximiliano.backend.service.TimeRecordService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/times")
public class TimeRecordController {

    @Autowired
    private TimeRecordService timeRecordService;

    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    @GetMapping
    public ResponseEntity<List<TimeRecordResponseDTO>> getAllTimeRecords() {
        List<TimeRecordResponseDTO> timeRecords = timeRecordService.getAllTimeRecords();
        return ResponseEntity.ok(timeRecords);
    }

    @PreAuthorize("hasRole('ADMIN') or (hasRole('MANAGER') or (hasRole('EMPLOYEE') and #id == principal.id))")
    @GetMapping("/{id}")
    public ResponseEntity<TimeRecordDetailsResponseDTO> getTimeRecordByID(
            @PathVariable Long id
    ) {
        TimeRecordDetailsResponseDTO timeRecord = timeRecordService.getTimeRecordByID(id);
        return ResponseEntity.ok(timeRecord);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE')")
    @PostMapping
    public ResponseEntity<TimeRecordResponseDTO> createNewTimeRecord(
         @Valid @RequestBody TimeRecordRequestDTO timeRecordRequestDTO
    ) {
        TimeRecordResponseDTO timeRecord = timeRecordService.createNewTimeRecord(timeRecordRequestDTO);
        URI uri = UriComponentsBuilder.fromPath("/api/times/{id}").buildAndExpand(timeRecord.id()).toUri();
        return ResponseEntity.created(uri).body(timeRecord);
    }
}
