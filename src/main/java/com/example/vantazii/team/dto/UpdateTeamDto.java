package com.example.vantazii.team.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UpdateTeamDto {
    private String teamName;
    private String teamDescription;
    private MultipartFile file;
}
