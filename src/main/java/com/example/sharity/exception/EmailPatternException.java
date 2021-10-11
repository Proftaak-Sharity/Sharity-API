package com.example.sharity.exception;

public class EmailPatternException extends RuntimeException {

//    IF AN EMAILADDRESS DOES NOT MEET THE RIGHT REQUIERMENT, THIS ERROR IS SHOWN
    public EmailPatternException(String value) {
        super(value + " is not a correct emailaddress");
    }
}
