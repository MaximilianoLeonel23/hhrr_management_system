package com.maximiliano.backend.controller;

import com.maximiliano.backend.dto.task.TaskDetailsResponseDTO;
import com.maximiliano.backend.dto.task.TaskRequestDTO;
import com.maximiliano.backend.dto.task.TaskResponseDTO;
import com.maximiliano.backend.dto.task.TaskUpdateRequestDTO;
import com.maximiliano.backend.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public ResponseEntity<List<TaskResponseDTO>> getAllTasks() {
        List<TaskResponseDTO> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDetailsResponseDTO> getTaskByID(@PathVariable Long id) {
        TaskDetailsResponseDTO task = taskService.getTaskByID(id);
        return ResponseEntity.ok(task);
    }

    @PostMapping
    public ResponseEntity<TaskResponseDTO> createNewTask(@RequestBody TaskRequestDTO taskRequestDTO) {
        TaskResponseDTO task = taskService.createNewTask(taskRequestDTO);
        URI uri = UriComponentsBuilder.fromPath("/api/tasks/{id}").buildAndExpand(task.id()).toUri();
        return ResponseEntity.created(uri).body(task);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> updateTask(
            @PathVariable Long id,
            @RequestBody TaskUpdateRequestDTO taskUpdateRequestDTO
    ) {
        TaskResponseDTO task = taskService.updateTask(id, taskUpdateRequestDTO);
        return ResponseEntity.ok(task);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
