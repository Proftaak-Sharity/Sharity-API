package com.example.sharity.exception;

public class InputNotAllowedException extends RuntimeException{

    public InputNotAllowedException(String key){
        super("Not allowed to manually add a " + key);
    }

}
