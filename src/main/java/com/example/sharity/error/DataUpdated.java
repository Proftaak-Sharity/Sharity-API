package com.example.sharity.error;

public class DataUpdated extends RuntimeException{

    public DataUpdated(String key) {
        super(key + "data updated succesfully");
    }
}
