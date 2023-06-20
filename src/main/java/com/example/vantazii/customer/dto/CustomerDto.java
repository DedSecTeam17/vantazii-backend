package com.example.vantazii.customer.dto;


import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.File;

@Data
public class CustomerDto {


    private  String  userName;
    private  String  email;

    @NotNull
    private  String  phoneNumber;


    private MultipartFile profileImage;
}

