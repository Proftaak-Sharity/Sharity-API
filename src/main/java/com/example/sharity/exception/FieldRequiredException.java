package com.example.sharity.exception;

public class FieldRequiredException extends RuntimeException {

//    IF A FIELD IS NULL OR EMPTY WHILE IT REQUIRES DATA, THIS ERROR IS SHOWN
    public FieldRequiredException(String key){
        super(key + " input is required");
    }
}
