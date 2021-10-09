package com.example.sharity.error;

public class AllNullError extends RuntimeException {

    public AllNullError() {
        super("No adjustments entered");
    }
}
