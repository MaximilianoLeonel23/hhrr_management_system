package com.maximiliano.backend.service;

import com.maximiliano.backend.dto.document.DocumentUploadResponseDTO;
import com.maximiliano.backend.model.Document;
import com.maximiliano.backend.model.Employee;
import com.maximiliano.backend.repository.DocumentRepository;
import com.maximiliano.backend.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;
import java.util.UUID;

@Service
public class FileStorageService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DocumentRepository documentRepository;

    private final Path rootLocation;

    public FileStorageService() {
        this.rootLocation = Paths.get("uploads").toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.rootLocation);
        } catch (Exception ex) {
            throw new RuntimeException("Could not create the root directory where the uploaded files will be stored.", ex);
        }
    }

    public DocumentUploadResponseDTO storeFile(MultipartFile file, Long employeeId) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            if (fileName.contains("..")) {
                throw new RuntimeException("Filename contains invalid path sequence " + fileName);
            }

            Optional<Employee> employee = employeeRepository.findById(employeeId);
            if (employee.isEmpty()) {
                throw new RuntimeException("Employee not found with id: " + employeeId);
            }

            String uniqueFileName = UUID.randomUUID().toString() + "_" + fileName;

            Path employeeDir = this.rootLocation.resolve(employeeId.toString());
            Files.createDirectories(employeeDir);

            Path targetLocation = employeeDir.resolve(uniqueFileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            Document document = new Document(employee.get(), fileName, targetLocation.toString());
            documentRepository.save(document);

            return new DocumentUploadResponseDTO(
                    "File uploaded successfully",
                    document.getFilePath(),
                    document.getTitle(),
                    employeeId,
                    document.getUploadDate(),
                    document.getCreatedAt(),
                    document.getUpdatedAt()
            );

        } catch (IOException ex) {
            throw new RuntimeException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public Resource loadFileAsResource(String fileName, Long employeeId) {
        try {
            Path employeeDir = this.rootLocation.resolve(employeeId.toString());

            Path filePath = employeeDir.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new RuntimeException("File not found " + fileName, ex);
        }
    }
}
