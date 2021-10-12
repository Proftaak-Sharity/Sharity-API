package com.example.sharity.errorHandling.car;

public class NotFoundException extends RuntimeException{

    public NotFoundException(String key, String value) {
        super(key + " not found: " + value);
    }
}
