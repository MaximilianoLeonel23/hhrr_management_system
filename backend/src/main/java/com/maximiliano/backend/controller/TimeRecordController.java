package com.maximiliano.backend.controller;


import com.maximiliano.backend.dto.times.TimeRecordDetailsResponseDTO;
import com.maximiliano.backend.dto.times.TimeRecordRequestDTO;
import com.maximiliano.backend.dto.times.TimeRecordResponseDTO;
import com.maximiliano.backend.service.TimeRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/times")
public class TimeRecordController {

    @Autowired
    private TimeRecordService timeRecordService;

    @GetMapping
    public ResponseEntity<List<TimeRecordResponseDTO>> getAllTimeRecords() {
        List<TimeRecordResponseDTO> timeRecords = timeRecordService.getAllTimeRecords();
        return ResponseEntity.ok(timeRecords);
    }

    @GetMapping
    public ResponseEntity<TimeRecordDetailsResponseDTO> getTimeRecordByID(
            @PathVariable Long id
    ) {
        TimeRecordDetailsResponseDTO timeRecord = timeRecordService.getTimeRecordByID(id);
        return ResponseEntity.ok(timeRecord);
    }

    @PostMapping
    public ResponseEntity<TimeRecordResponseDTO> createNewTimeRecord(
            @RequestBody TimeRecordRequestDTO timeRecordRequestDTO
    ) {
        TimeRecordResponseDTO timeRecord = timeRecordService.createNewTimeRecord(timeRecordRequestDTO);
        URI uri = UriComponentsBuilder.fromPath("/api/times/{id}").buildAndExpand(timeRecord.id()).toUri();
        return ResponseEntity.created(uri).body(timeRecord);
    }
}
