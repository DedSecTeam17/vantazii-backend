package com.example.vantazii.league.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

@Data
public class UpdateLeagueDto {
    private String leagueName;
    private MultipartFile file;
    private String leagueDescription;
}
