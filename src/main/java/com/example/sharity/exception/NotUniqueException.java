package com.example.sharity.exception;

public class NotUniqueException extends RuntimeException {

//    IF DATA NEEDS TO BE UNIQUE, BUT ISN'T, THIS ERROR WILL BE SHOWN
    public NotUniqueException(String key){
        super(key + " is already connected to another customer");
    }
}
