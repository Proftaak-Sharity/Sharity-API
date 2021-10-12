package com.example.sharity.errorHandling.customer;

public class EmptyValueException extends RuntimeException {

    public EmptyValueException(String key) {
        super(key + " was empty");
    }
}
