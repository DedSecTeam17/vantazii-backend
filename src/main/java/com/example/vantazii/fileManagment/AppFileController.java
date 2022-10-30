package com.example.vantazii.fileManagment;


import com.example.vantazii.core.config.FileManagmentConfig;
import com.example.vantazii.core.exception.ApiRequestException;
import com.example.vantazii.core.exception.CustomStatus.ApiExceptionType;
import com.example.vantazii.utils.filesManagments.FileManagmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
@RequestMapping("api/v1/files")
@AllArgsConstructor
public class AppFileController {

    FileManagmentService fileManagmentService;
    FileManagmentConfig fileManagmentConfig;

    @GetMapping(path = "/preview/{fileName}")
    public ResponseEntity<byte[]> preview(@PathVariable String fileName) throws IOException {
        try {

            File file = fileManagmentService.getFileByName(fileName);

            System.out.println(file.toURL().openConnection().getContentType());
            MediaType contentType = MediaType.parseMediaType(file.toURL().openConnection().getContentType());

            return ResponseEntity.ok()
                    .contentType(contentType)
                    .body(Files.readAllBytes(Path.of(file.getPath())));
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage(), e.getCause(), ApiExceptionType.DEFAULT);
        }
    }
}
