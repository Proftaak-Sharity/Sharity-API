package com.example.sharity.error;

public class NotFoundError extends RuntimeException{

    public NotFoundError(String key, Long value) {
        super(key + " not found: " + value);
    }
}
