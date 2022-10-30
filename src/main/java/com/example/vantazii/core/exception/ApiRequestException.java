package com.example.vantazii.core.exception;

import com.example.vantazii.core.exception.CustomStatus.ApiExceptionType;
import lombok.Data;

@Data
public class ApiRequestException extends  RuntimeException{
    private ApiExceptionType apiExceptionType;
    public ApiRequestException(String message) {
        super(message);
    }

    public ApiRequestException(String message, ApiExceptionType apiExceptionType) {
        super(message);
        this.apiExceptionType = apiExceptionType;
    }

    public ApiRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiRequestException(String message, Throwable cause,ApiExceptionType apiExceptionType) {
        super(message, cause);
        this.apiExceptionType = apiExceptionType;

    }
}
