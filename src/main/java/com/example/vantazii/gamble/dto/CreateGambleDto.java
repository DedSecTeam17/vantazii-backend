package com.example.vantazii.gamble.dto;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CreateGambleDto {

    @NotNull
    @NotBlank
    private String matchId;

    @NotNull
    @NotBlank
    private String expectedResult;
}
