package com.example.vantazii.utils.filesManagments;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
@AllArgsConstructor
public class FileManagmentService {
    private final Path root = Paths.get("src/main/resources/uploads");


    private String generateFileName(String fileName) {
        long time = System.currentTimeMillis() / 1000;
        String pathWithoutExtenstion = com.google.common.io.Files.getFileExtension(fileName);
        return time + "." + pathWithoutExtenstion;
    }

    public String uploadFile(MultipartFile file) {

        String fileName = generateFileName(file.getOriginalFilename());
        try {
            Files.copy(file.getInputStream(), this.root.resolve(fileName));
        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
        return fileName;
    }

    public boolean  deleteFile(String fileName){
        File file = this.getFileByName(fileName);
        System.out.println(file.getPath());
       return file.delete();
    }


    public File getFileByName(String fileName) {
        return this.root.resolve(fileName).toFile();
    }
}
