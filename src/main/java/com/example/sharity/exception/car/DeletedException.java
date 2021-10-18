package com.example.sharity.exception.car;

public class DeletedException extends RuntimeException {

    public DeletedException(String key) {
        super(key + " deleted succesfully");
    }
}
