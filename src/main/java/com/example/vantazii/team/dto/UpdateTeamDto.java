package com.example.vantazii.team.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UpdateTeamDto {
    private String teamName;
    private String teamDescription;
    private MultipartFile file;
}
