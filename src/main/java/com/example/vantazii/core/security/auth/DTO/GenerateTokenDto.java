package com.example.vantazii.core.security.auth.DTO;

import com.example.vantazii.core.CustomAnnotation.phoneNumber.PhoneNumber;
import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class GenerateTokenDto {

    @PhoneNumber
    private String phoneNumber;

    @Size(max = 4,message = "invalid code")
    @Size(min = 4,message = "invalid code")
    private String token;

    private void test(){

    }
}
