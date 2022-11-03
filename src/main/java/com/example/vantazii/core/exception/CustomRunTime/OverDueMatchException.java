package com.example.vantazii.core.exception.CustomRunTime;

public class OverDueMatchException extends Exception{
    String message;

    public OverDueMatchException(String message) {
        super(message);
        this.message = message;
    }
}
