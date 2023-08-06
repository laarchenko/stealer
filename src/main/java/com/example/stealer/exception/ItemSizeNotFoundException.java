package com.example.stealer.exception;

import org.springframework.http.HttpStatus;

public class ItemSizeNotFoundException extends ApplicationException{

    public ItemSizeNotFoundException() {
        super(HttpStatus.BAD_REQUEST, "Item price not found exception !");
    }

    public ItemSizeNotFoundException(String errorMessage) {
        super(HttpStatus.BAD_REQUEST, errorMessage);
    }
}
