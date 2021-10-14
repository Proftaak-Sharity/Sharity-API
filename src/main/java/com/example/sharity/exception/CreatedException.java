package com.example.sharity.exception;

public class CreatedException extends RuntimeException {

    public CreatedException(String key) {
        super(key + " added succesfully");
    }
}
