package com.example.vantazii.core.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;
@Data
@AllArgsConstructor
public class ApiException<T>{
    private final Throwable throwable;
    private  final HttpStatus httpStatus;
    private final ZonedDateTime zonedDateTime;
    private final T details;
}
