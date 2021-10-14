package com.example.sharity.exception;

public class UpdateNotAllowedException extends RuntimeException {

    public UpdateNotAllowedException(String key) {
        super("It's not allowed to update " + key);
    }
}
