package com.example.sharity.exception;

public class CrudException extends RuntimeException{

//    IF DATA IS INSERTED, UPDATED OR DELETED SUCCESFULLY, THIS EXCEPTION IS SHOWN
    public CrudException(String key, String crud) {
        super(key + " " + crud + "d succesfully");
    }
}
