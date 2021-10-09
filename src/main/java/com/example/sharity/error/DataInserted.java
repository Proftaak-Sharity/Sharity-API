package com.example.sharity.error;

public class DataInserted extends RuntimeException{

    public DataInserted(String key){
        super(key + "data added succesfully");
    }
}
