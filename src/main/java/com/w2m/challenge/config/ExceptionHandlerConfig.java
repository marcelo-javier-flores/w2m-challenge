package com.w2m.challenge.config;

import com.w2m.challenge.exception.BasicApiException;
import com.w2m.challenge.exception.model.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerConfig extends ResponseEntityExceptionHandler {

    private static final String PREFIX_ERROR_CODE = "w2m-challenge-";

    @ExceptionHandler(value = BasicApiException.class)
    public ResponseEntity<Object> handleAllUncaughtException(
            BasicApiException ex,
            WebRequest request) {

        return generateBody(ex, ex.getStatus());
    }

    private ResponseEntity<Object> generateBody(Exception ex, HttpStatus statusCode) {
        return new ResponseEntity<>(
                new Error(
                        PREFIX_ERROR_CODE + String.format("%05d", statusCode.value()),
                        ex.getMessage()
                ),
                statusCode
        );
    }
}
