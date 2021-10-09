package com.example.sharity.errorHandling;

public class NoChangesStringException extends RuntimeException{

    public NoChangesStringException(String key, String value) {
        super(key + " already used by you: " + value);
    }
}
