package com.maximiliano.backend.service;

import com.maximiliano.backend.dto.employee.EmployeeDetailsResponseDTO;
import com.maximiliano.backend.dto.employee.EmployeeResponseDTO;
import com.maximiliano.backend.dto.project.ProjectResponseDTO;
import com.maximiliano.backend.dto.task.TaskDetailsResponseDTO;
import com.maximiliano.backend.dto.task.TaskRequestDTO;
import com.maximiliano.backend.dto.task.TaskResponseDTO;
import com.maximiliano.backend.dto.task.TaskUpdateRequestDTO;
import com.maximiliano.backend.exception.employee.DuplicateEmployeeEmailException;
import com.maximiliano.backend.exception.employee.EmployeeNotFoundException;
import com.maximiliano.backend.exception.employee.NoEmployeesFoundException;
import com.maximiliano.backend.exception.project.ProjectNotFoundException;
import com.maximiliano.backend.exception.task.NoTasksFoundException;
import com.maximiliano.backend.exception.task.TaskNotFoundException;
import com.maximiliano.backend.model.Employee;
import com.maximiliano.backend.model.Project;
import com.maximiliano.backend.model.Task;
import com.maximiliano.backend.repository.EmployeeRepository;
import com.maximiliano.backend.repository.ProjectRepository;
import com.maximiliano.backend.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public List<TaskResponseDTO> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        if (!tasks.isEmpty()) {
            return tasks.stream().map(t -> new TaskResponseDTO(
                    t.getId(),
                    t.getTitle(),
                    t.getDescription(),
                    t.getAssignedTo().getId(),
                    t.getProject().getId(),
                    t.getDueDate(),
                    t.getStatus(),
                    t.getCreatedAt(),
                    t.getUpdatedAt()
            )).toList();
        } else {
            throw new NoTasksFoundException("No tasks found.");
        }
    }

    public TaskDetailsResponseDTO getTaskByID(Long id) {
        Optional<Task> taskFound = taskRepository.findById(id);
        if (taskFound.isPresent()) {
            Task task = taskFound.get();
            Employee employee = task.getAssignedTo();
            EmployeeResponseDTO employeeResponseDTO = new EmployeeResponseDTO(
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
            Project project = task.getProject();
            ProjectResponseDTO projectResponseDTO = new ProjectResponseDTO(
                    project.getId(),
                    project.getName(),
                    project.getDescription(),
                    project.getStartDate(),
                    project.getEndDate(),
                    project.getCreatedAt(),
                    project.getUpdatedAt()
            );

            return new TaskDetailsResponseDTO(
                    task.getId(),
                    task.getTitle(),
                    task.getDescription(),
                    employeeResponseDTO,
                    projectResponseDTO,
                    task.getDueDate(),
                    task.getStatus(),
                    task.getCreatedAt(),
                    task.getUpdatedAt()
            );
        } else {
            throw new TaskNotFoundException("Task with ID " + id + " not found.");
        }
    }

    public TaskResponseDTO createNewTask(TaskRequestDTO taskRequestDTO) {
        Optional<Employee> employeeFound = employeeRepository.findById(taskRequestDTO.assignedTo());
        if (employeeFound.isEmpty()) {
            throw new EmployeeNotFoundException("Task with assigned to employee ID " + taskRequestDTO.assignedTo() + " not found.");
        }
        Optional<Project> projectFound = projectRepository.findById(taskRequestDTO.project());
        if (projectFound.isEmpty()) {
            throw new ProjectNotFoundException("Task with project ID " + taskRequestDTO.project() + " not found.");
        }
        Employee employee = employeeFound.get();
        Project project = projectFound.get();
        Task newTask = new Task(taskRequestDTO, employee, project);
        Task task = taskRepository.save(newTask);
        return new TaskResponseDTO(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getAssignedTo().getId(),
                task.getProject().getId(),
                task.getDueDate(),
                task.getStatus(),
                task.getCreatedAt(),
                task.getUpdatedAt()
        );
    }

    public TaskResponseDTO updateTask(Long id, TaskUpdateRequestDTO taskUpdateRequestDTO) {
        Optional<Task> taskFound = taskRepository.findById(id);
        if (taskFound.isPresent()) {
            Task task = taskFound.get();
            task.update(taskUpdateRequestDTO, task.getAssignedTo(), task.getProject());
            taskRepository.save(task);
            return new TaskResponseDTO(
                    task.getId(),
                    task.getTitle(),
                    task.getDescription(),
                    task.getAssignedTo().getId(),
                    task.getProject().getId(),
                    task.getDueDate(),
                    task.getStatus(),
                    task.getCreatedAt(),
                    task.getUpdatedAt()
            );
        } else {
            throw new TaskNotFoundException("Task with ID " + id + " not found.");
        }
    }

    public void deleteTask(Long id) {
        Optional<Task> taskFound = taskRepository.findById(id);
        if (taskFound.isEmpty()) {
            throw new TaskNotFoundException("Task with ID " + id + " not found.");
        }
        taskRepository.deleteById(taskFound.get().getId());
    }
}
