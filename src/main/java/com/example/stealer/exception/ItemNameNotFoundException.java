package com.example.stealer.exception;

import org.springframework.http.HttpStatus;

public class ItemNameNotFoundException extends ApplicationException{


    public ItemNameNotFoundException() {
        super(HttpStatus.BAD_REQUEST, "Item name not found exception !");
    }

    public ItemNameNotFoundException(String errorMessage) {
        super(HttpStatus.BAD_REQUEST, errorMessage);
    }
}
