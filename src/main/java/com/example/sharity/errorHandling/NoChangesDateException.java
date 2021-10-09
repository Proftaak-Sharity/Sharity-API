package com.example.sharity.errorHandling;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class NoChangesDateException extends RuntimeException{

    public NoChangesDateException(String key, LocalDate value) {
        super(key + " already used by you: " + value.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)));
    }
}
