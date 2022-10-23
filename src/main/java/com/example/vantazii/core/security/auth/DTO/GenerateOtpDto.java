package com.example.vantazii.core.security.auth.DTO;


import com.example.vantazii.core.CustomAnnotation.phoneNumber.PhoneNumber;
import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
public class GenerateOtpDto {

    @PhoneNumber
    private String phoneNumber;
}
