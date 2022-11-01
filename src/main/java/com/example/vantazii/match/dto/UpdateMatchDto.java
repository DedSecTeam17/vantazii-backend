package com.example.vantazii.match.dto;


import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class UpdateMatchDto {


    @DateTimeFormat
    private String startTime;

    @DateTimeFormat
    private String endTime;
    private String result;
}
