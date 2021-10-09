package com.example.sharity.error;

public class NoChangesStringError extends RuntimeException{

    public NoChangesStringError(String key, String value) {
        super(key + " already used by you: " + value);
    }
}
