package com.example.vantazii.DTO;


import lombok.Data;

@Data
public class GlobalResponseBody<T> {


    private  boolean status;
    private  Error error;
    private  T data;


      @Data
    public static class  Error {
          private  String message;
          private  String errorCode;
    }
}



