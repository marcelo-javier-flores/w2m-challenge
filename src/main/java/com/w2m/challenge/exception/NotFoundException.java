package com.w2m.challenge.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends BasicApiException{
    public NotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
