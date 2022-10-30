package com.example.vantazii.team.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CreateTeamDto {

    @NotNull
    @NotBlank
    private String teamName;

    @NotNull
    @NotBlank
    private String teamDescription;

    @NotNull
    @NotBlank
    private String leagueId;

    @NotNull
    private MultipartFile file;
}
