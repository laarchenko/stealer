package com.example.stealer.exception;

import org.springframework.http.HttpStatus;

public class InvalidUrlException extends ApplicationException{

    public InvalidUrlException(String url) {
        super(HttpStatus.BAD_REQUEST, String.format("Incorrect url format for '%s'", url));
    }
}
