package com.example.vantazii.match.dto;


import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class CreateMatchDto {

    @NotBlank
    @NotNull
    private String leagueId;

    @NotBlank
    @NotNull
    private String homeTeamId;

    @NotBlank
    @NotNull
    private String awayTeamId;

    @NotBlank
    @NotNull
    @DateTimeFormat
    private String startTime;

    @NotBlank
    @NotNull
    @DateTimeFormat
    private String endTime;
}
