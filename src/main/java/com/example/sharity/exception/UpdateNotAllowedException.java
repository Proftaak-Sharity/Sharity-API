package com.example.sharity.exception;

public class UpdateNotAllowedException extends RuntimeException {

    public UpdateNotAllowedException(String key) {
        super("It's not allowed to update " + key);
    }

    public UpdateNotAllowedException(String key, String iban, Long customerNumber) {
        super(key + " with iban: '" + iban + " does not belong tot customer: " + customerNumber);
    }
}
