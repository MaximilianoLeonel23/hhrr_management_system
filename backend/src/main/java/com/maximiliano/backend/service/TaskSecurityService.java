package com.maximiliano.backend.service;

import com.maximiliano.backend.dto.task.TaskDetailsResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskSecurityService {

    @Autowired
    private TaskService taskService;

    public boolean isTaskAssignedToEmployee(Long taskId, Long employeeId) {
        TaskDetailsResponseDTO task = taskService.getTaskByID(taskId);
        return task.assignedTo().id().equals(employeeId);
    }
}
