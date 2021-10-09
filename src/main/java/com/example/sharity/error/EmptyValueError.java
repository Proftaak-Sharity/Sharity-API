package com.example.sharity.error;

public class EmptyValueError extends RuntimeException {

    public EmptyValueError(String key) {
        super(key + " was empty");
    }
}
