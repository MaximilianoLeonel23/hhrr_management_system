package com.maximiliano.backend.service;

import com.maximiliano.backend.dto.project.ProjectDetailsResponseDTO;
import com.maximiliano.backend.dto.project.ProjectRequestDTO;
import com.maximiliano.backend.dto.project.ProjectResponseDTO;
import com.maximiliano.backend.dto.project.ProjectUpdateRequestDTO;
import com.maximiliano.backend.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    public List<ProjectResponseDTO> getAllProjects() {
        return null;
    }

    public ProjectDetailsResponseDTO getProjectByID(Long id) {
        return null;
    }

    public ProjectResponseDTO createNewProject(ProjectRequestDTO projectRequestDTO) {
        return null;
    }

    public ProjectResponseDTO updateProject(Long id, ProjectUpdateRequestDTO projectUpdateRequestDTO) {
        return null;
    }

    public void deleteProject(Long id) {
    }
}
