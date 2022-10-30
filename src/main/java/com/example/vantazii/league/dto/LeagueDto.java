package com.example.vantazii.league.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;


@Data
public class LeagueDto {
    @NotNull
    private String leagueName;

    @NotNull
    private String leagueDescription;

    @NotNull
    private MultipartFile file;
}
