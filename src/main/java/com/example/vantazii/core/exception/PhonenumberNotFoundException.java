package com.example.vantazii.core.exception;




public class PhonenumberNotFoundException extends org.springframework.security.core.AuthenticationException {
    public PhonenumberNotFoundException(String msg) {
        super(msg);
    }

    public PhonenumberNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
