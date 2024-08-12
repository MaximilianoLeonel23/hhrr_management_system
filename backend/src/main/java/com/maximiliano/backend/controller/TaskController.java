package com.maximiliano.backend.controller;

import com.maximiliano.backend.dto.task.TaskDetailsResponseDTO;
import com.maximiliano.backend.dto.task.TaskRequestDTO;
import com.maximiliano.backend.dto.task.TaskResponseDTO;
import com.maximiliano.backend.dto.task.TaskUpdateRequestDTO;
import com.maximiliano.backend.service.TaskSecurityService;
import com.maximiliano.backend.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskSecurityService taskSecurityService;

    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    @GetMapping
    public ResponseEntity<List<TaskResponseDTO>> getAllTasks() {
        List<TaskResponseDTO> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }

    @PreAuthorize("hasRole('ADMIN') or (hasRole('MANAGER') or (hasRole('EMPLOYEE') and @taskSecurityService.isTaskAssignedToEmployee(#id, principal.id)))")
    @GetMapping("/{id}")
    public ResponseEntity<TaskDetailsResponseDTO> getTaskByID(@PathVariable Long id) {
        TaskDetailsResponseDTO task = taskService.getTaskByID(id);
        return ResponseEntity.ok(task);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    @PostMapping
    public ResponseEntity<TaskResponseDTO> createNewTask(@RequestBody TaskRequestDTO taskRequestDTO) {
        TaskResponseDTO task = taskService.createNewTask(taskRequestDTO);
        URI uri = UriComponentsBuilder.fromPath("/api/tasks/{id}").buildAndExpand(task.id()).toUri();
        return ResponseEntity.created(uri).body(task);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> updateTask(
            @PathVariable Long id,
            @RequestBody TaskUpdateRequestDTO taskUpdateRequestDTO
    ) {
        TaskResponseDTO task = taskService.updateTask(id, taskUpdateRequestDTO);
        return ResponseEntity.ok(task);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
