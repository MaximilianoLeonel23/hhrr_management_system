package com.maximiliano.backend.service;

import com.maximiliano.backend.dto.task.TaskDetailsResponseDTO;
import com.maximiliano.backend.dto.task.TaskRequestDTO;
import com.maximiliano.backend.dto.task.TaskResponseDTO;
import com.maximiliano.backend.dto.task.TaskUpdateRequestDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {


    public List<TaskResponseDTO> getAllTasks() {
        return null;
    }

    public TaskDetailsResponseDTO getTaskByID(Long id) {
        return null;
    }

    public TaskResponseDTO createNewTask(TaskRequestDTO taskRequestDTO) {
        return null;
    }

    public TaskResponseDTO updateTask(Long id, TaskUpdateRequestDTO taskUpdateRequestDTO) {
        return null;
    }

    public void deleteTask(Long id) {
    }
}
