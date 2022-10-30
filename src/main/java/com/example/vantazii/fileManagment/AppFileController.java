package com.example.vantazii.fileManagment;


import com.example.vantazii.core.config.FileManagmentConfig;
import com.example.vantazii.core.exception.ApiRequestException;
import com.example.vantazii.core.exception.CustomStatus.ApiExceptionType;
import com.example.vantazii.utils.filesManagments.FileManagmentService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;

@RestController
@RequestMapping("api/v1/files")
@AllArgsConstructor
public class AppFileController {

    FileManagmentService fileManagmentService;
    FileManagmentConfig fileManagmentConfig;

    @GetMapping(path = "/preview/{fileName}")
    public ResponseEntity<InputStreamResource> preview(@PathVariable String fileName) {
        try {

            MediaType contentType = MediaType.IMAGE_JPEG;
            InputStream in =
                    getClass().getResourceAsStream(fileManagmentConfig.getFolderName()+"/" + fileName);
            return ResponseEntity.ok()
                    .contentType(contentType)
                    .body(new InputStreamResource(in));
        } catch (Exception e) {
            throw new ApiRequestException("File not found",e.getCause(), ApiExceptionType.DEFAULT);
        }
    }
}
