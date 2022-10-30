package com.example.vantazii.core.security.auth.DTO;


import com.example.vantazii.core.CustomAnnotation.phoneNumber.PhoneNumber;
import lombok.Data;

@Data
public class GenerateOtpDto {

    @PhoneNumber
    private String phoneNumber;
}
