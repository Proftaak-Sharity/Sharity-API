package com.example.sharity.exception;

public class NotFoundException extends RuntimeException {

    //    IF THE REQUESTED DATA IS NOT IN DATABASE, THIS ERROR IS SHOWN
    public NotFoundException(String key, Long value) {
        super(key + " not found: " + value);
    }

    public NotFoundException(String key) {
        super(key + " not found ");
    }
}