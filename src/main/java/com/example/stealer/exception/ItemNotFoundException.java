package com.example.stealer.exception;

import org.springframework.http.HttpStatus;

public class ItemNotFoundException extends ApplicationException{

    public ItemNotFoundException(Long id) {
        super(HttpStatus.BAD_REQUEST, String.format("Item with id '%s' not found", id));
    }
}
