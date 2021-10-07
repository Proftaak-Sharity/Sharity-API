package com.example.sharity.abstracts;

import java.util.regex.Pattern;

public abstract class EmailValidator {

    public static boolean patternMatches(String emailAddress, String regexPattern) {
        return Pattern.compile(regexPattern)
                .matcher(emailAddress)
                .matches();
    }
}
