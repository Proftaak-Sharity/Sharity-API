package com.example.sharity.exception.car;

public class NotFoundException extends RuntimeException{

    public NotFoundException(String key, String value) {
        super(key + " with licence plate: " + value + " not found");
    }
}
