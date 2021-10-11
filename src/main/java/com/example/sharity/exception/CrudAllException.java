package com.example.sharity.exception;

public class CrudAllException extends RuntimeException {

//    IF USER TRIES TO UPDATE/DELETE ALL DATA ROWS AT ONCE, THIS ERROR WILL BE SHOWN
    public CrudAllException(String crud, String key) {
        super("It's not allowed to " + crud + " all " + key);
    }
}
