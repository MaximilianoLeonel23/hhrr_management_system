package com.maximiliano.backend.controller;

import com.maximiliano.backend.dto.document.DocumentUploadResponseDTO;
import com.maximiliano.backend.service.FileStorageService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/documents")
public class DocumentController {

    @Autowired
    private FileStorageService fileStorageService;

    @PreAuthorize("hasRole('ADMIN') or (hasRole('MANAGER')) or (hasRole('EMPLOYEE') and #employeeId == principal.id)")
    @PostMapping("/upload")
    public ResponseEntity<DocumentUploadResponseDTO> uploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("employee") Long employeeId
    ) {
        DocumentUploadResponseDTO newFile = fileStorageService.storeFile(file, employeeId);
        return ResponseEntity.status(HttpStatus.CREATED).body(newFile);
    }

    @PreAuthorize("hasRole('ADMIN') or (hasRole('MANAGER')) or (hasRole('EMPLOYEE') and #employeeId == principal.id)")
    @GetMapping("/download/{employeeId}/{fileName}")
    public ResponseEntity<Resource> downloadFile(
            @PathVariable Long employeeId,
            @PathVariable String fileName,
            HttpServletRequest request
    ) {
        Resource resource = fileStorageService.loadFileAsResource(fileName, employeeId);


        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}

