package com.example.vantazii.core.shared;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;


@Data
@AllArgsConstructor
public class DefaultResponse {
    private String message;
    private HttpStatus httpStatus;
}
