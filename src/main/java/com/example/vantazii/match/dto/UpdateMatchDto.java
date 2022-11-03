package com.example.vantazii.match.dto;


import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class UpdateMatchDto {


    @DateTimeFormat
    private String startTime;

    @DateTimeFormat
    private String endTime;
    private String result;
}
