package com.example.sharity.error;

public class NotUniqueError extends RuntimeException {

    public NotUniqueError(String key){
        super(key + " is already used by another customer");
    }
}
