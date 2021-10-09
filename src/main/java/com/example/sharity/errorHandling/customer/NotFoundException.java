package com.example.sharity.errorHandling.customer;

public class NotFoundException extends RuntimeException{

    public NotFoundException(String key, Long value) {
        super(key + " not found: " + value);
    }
}
