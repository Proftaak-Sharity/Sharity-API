package com.example.sharity.exception;

public class AllNullException extends RuntimeException {

//    IF ALLE FIELDS ARE NULL, THIS ERROR WILL BE SHOWN
    public AllNullException() {
        super("All fields were empty");
    }
}
