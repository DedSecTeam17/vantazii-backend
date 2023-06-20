package com.example.vantazii.core.exception;

import com.example.vantazii.DTO.GlobalResponseBody;
import com.example.vantazii.core.exception.ExceptionDetails.GenralDetails;
import com.example.vantazii.core.security.auth.DTO.NewCustomerResponse;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;


@ControllerAdvice
@ToString
public class ApiExceptionHandeler {

    @ExceptionHandler(value = ApiRequestException.class)
    ResponseEntity<Object> handelApiException(ApiRequestException e) {

        ApiException<?> apiException = null;
        switch (e.getApiExceptionType()) {

            case DEFAULT:
                apiException = new ApiException<String>(
                        e.getCause(),
                        HttpStatus.BAD_REQUEST,
                        ZonedDateTime.now(),
                        e.getMessage());
                break;
            case NEW_CUSTOMER_TO_APP:


                apiException = new ApiException<String>(
                        e.getCause(),
                        HttpStatus.OK,
                        ZonedDateTime.now(),
                        e.getMessage());
                break;
            case NOT_FOUND:

                apiException = new ApiException<String>(
                        e.getCause(),
                        HttpStatus.NOT_FOUND,
                        ZonedDateTime.now(),
                       e.getMessage());

            default:
                apiException = new ApiException<String>(
                        e.getCause(),
                        HttpStatus.OK,
                        ZonedDateTime.now(),
                        e.getMessage());

        }

        GlobalResponseBody<ApiException> globalResponseBody = new  GlobalResponseBody<ApiException>();
        globalResponseBody.setStatus(false);


        GlobalResponseBody.Error error = new GlobalResponseBody.Error();


        error.setErrorCode(apiException.getHttpStatus().getReasonPhrase());
        error.setMessage(apiException.getDetails().toString());
        globalResponseBody.setError(error);


        return new ResponseEntity<>(globalResponseBody, apiException.getHttpStatus());
    }


    @ExceptionHandler(value = NotFoundException.class)
    ResponseEntity<Object> handelNotFoundException(NotFoundException e) {
        ApiException<GenralDetails> apiException = new ApiException<GenralDetails>(
                e.getCause(),
                HttpStatus.NOT_FOUND,
                ZonedDateTime.now(),
                new GenralDetails(e.getMessage())
        );
        return new ResponseEntity<>(apiException, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(value = {Exception.class})
    ResponseEntity<Object> handleRunTimeException(RuntimeException e) {
        ApiException<GenralDetails> apiException = new ApiException<GenralDetails>(
                e.getCause(),
                HttpStatus.CONFLICT,
                ZonedDateTime.now(),
                new GenralDetails(e.getMessage())
        );
        return new ResponseEntity<>(apiException, HttpStatus.CONFLICT);
    }
}
