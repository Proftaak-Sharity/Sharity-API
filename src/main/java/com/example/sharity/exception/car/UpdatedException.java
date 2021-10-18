package com.example.sharity.exception.car;

public class UpdatedException extends RuntimeException {

    public UpdatedException(String key) {
        super(key + " updated succesfully");
    }
}
