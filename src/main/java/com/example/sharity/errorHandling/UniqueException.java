package com.example.sharity.errorHandling;

public class UniqueException extends RuntimeException {

    public UniqueException(String input){
        super(input + " is already used by another customer");
    }
}
