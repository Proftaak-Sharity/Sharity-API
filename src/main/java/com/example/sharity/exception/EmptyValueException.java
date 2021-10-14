package com.example.sharity.exception;

public class EmptyValueException extends RuntimeException {

//    IF A DATAFIELD IS EMPTY WHILE IT REQUIRES DATA OR NULL, THIS ERROR IS SHOWN
    public EmptyValueException(String key) {
        super(key + " cannot be empty");
    }
}
