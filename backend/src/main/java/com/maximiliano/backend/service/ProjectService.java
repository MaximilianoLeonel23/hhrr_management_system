package com.maximiliano.backend.service;

import com.maximiliano.backend.dto.employee.EmployeeDetailsResponseDTO;
import com.maximiliano.backend.dto.employee.EmployeeResponseDTO;
import com.maximiliano.backend.dto.project.ProjectDetailsResponseDTO;
import com.maximiliano.backend.dto.project.ProjectRequestDTO;
import com.maximiliano.backend.dto.project.ProjectResponseDTO;
import com.maximiliano.backend.dto.project.ProjectUpdateRequestDTO;
import com.maximiliano.backend.exception.employee.DuplicateEmployeeEmailException;
import com.maximiliano.backend.exception.employee.EmployeeNotFoundException;
import com.maximiliano.backend.exception.project.NoProjectsFoundException;
import com.maximiliano.backend.exception.project.ProjectNotFoundException;
import com.maximiliano.backend.model.Employee;
import com.maximiliano.backend.model.Project;
import com.maximiliano.backend.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public List<ProjectResponseDTO> getAllProjects() {
        List<Project> projects = projectRepository.findAll();
        if (!projects.isEmpty()) {
            return projects.stream().map(project -> new ProjectResponseDTO(
                    project.getId(),
                    project.getName(),
                    project.getDescription(),
                    project.getStartDate(),
                    project.getEndDate(),
                    project.getCreatedAt(),
                    project.getUpdatedAt()
            )).toList();
        } else {
            throw new NoProjectsFoundException("No projects found.");
        }
    }

    public ProjectDetailsResponseDTO getProjectByID(Long id) {
        Optional<Project> projectFound = projectRepository.findById(id);
        if (projectFound.isPresent()) {
            Project project = projectFound.get();
            List<EmployeeResponseDTO> employees = project.getEmployees().stream().map(e -> new EmployeeResponseDTO(
                    e.getId(),
                    e.getFirstName(),
                    e.getLastName(),
                    e.getEmail(),
                    e.getDepartment(),
                    e.getRole(),
                    e.getDateOfHire(),
                    e.getSalary(),
                    e.getCreatedAt(),
                    e.getUpdatedAt()
            )).toList();

            return new ProjectDetailsResponseDTO(
                    project.getId(),
                    project.getName(),
                    project.getDescription(),
                    project.getStartDate(),
                    project.getEndDate(),
                    project.getCreatedAt(),
                    project.getUpdatedAt(),
                    employees
            );
        } else {
            throw new ProjectNotFoundException("Project with ID " + id + " not found.");
        }
    }

    public ProjectResponseDTO createNewProject(ProjectRequestDTO projectRequestDTO) {
        Project newProject = new Project(projectRequestDTO);

        Project project = projectRepository.save(newProject);
        return new ProjectResponseDTO(
                project.getId(),
                project.getName(),
                project.getDescription(),
                project.getStartDate(),
                project.getEndDate(),
                project.getCreatedAt(),
                project.getUpdatedAt()
        );
    }

    public ProjectResponseDTO updateProject(Long id, ProjectUpdateRequestDTO projectUpdateRequestDTO) {
        Optional<Project> projectFound = projectRepository.findById(id);
        if (projectFound.isPresent()) {
            Project project = projectFound.get();
            project.update(projectUpdateRequestDTO);
            projectRepository.save(project);
            return new ProjectResponseDTO(
                    project.getId(),
                    project.getName(),
                    project.getDescription(),
                    project.getStartDate(),
                    project.getEndDate(),
                    project.getCreatedAt(),
                    project.getUpdatedAt()
            );
        } else {
            throw new EmployeeNotFoundException("Project with ID " + id + " not found.");
        }
    }

    public void deleteProject(Long id) {
        Optional<Project> projectFound = projectRepository.findById(id);
        if (projectFound.isEmpty()) {
            throw new ProjectNotFoundException("Project with ID " + id + " not found.");
        }
        projectRepository.deleteById(projectFound.get().getId());
    }
}
