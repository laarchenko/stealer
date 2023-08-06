package com.example.stealer.exception;

import org.springframework.http.HttpStatus;

public class ItemPriceNotFoundException extends ApplicationException{

    public ItemPriceNotFoundException() {
        super(HttpStatus.BAD_REQUEST, "Item price not found exception !");
    }

    public ItemPriceNotFoundException(String errorMessage) {
        super(HttpStatus.BAD_REQUEST, errorMessage);
    }
}
