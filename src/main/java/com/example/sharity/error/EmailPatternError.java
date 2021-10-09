package com.example.sharity.error;

public class EmailPatternError extends RuntimeException {

    public EmailPatternError(String value) {
        super("Email address " + value + " does not meet set requirements.");
    }
}
