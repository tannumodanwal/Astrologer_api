package com.api.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;



public class FileService {    
	private final String uploadDir = "uploads/";
    public String uploadImage(MultipartFile file) throws IOException {
        String fileName = UUID.randomUUID().toString()+ "_" +file.getOriginalFilename();
        Path filePath = Paths.get(uploadDir + fileName);
        Files.createDirectories(filePath.getParent());
        Files.write(filePath, file.getBytes());
        return fileName;
    }
}
