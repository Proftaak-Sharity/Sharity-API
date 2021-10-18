package com.example.sharity.exception;

public class DeleteNotAllowedException extends RuntimeException {

    public DeleteNotAllowedException(String key) {
        super("It's not allowed to delete " + key);
    }

}
