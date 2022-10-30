package com.example.vantazii.core.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "edm")
@Data
public class FileManagmentConfig {
    private String pathToPreviewFiles;
    private String folderName;
}
