package com.example.sharity.error;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class NoChangesDateError extends RuntimeException{

    public NoChangesDateError(String key, LocalDate value) {
        super(key + " already used by you: " + value.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)));
    }
}
