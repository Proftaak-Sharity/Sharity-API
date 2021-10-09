package com.example.sharity.entity.customer;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class EmailValidator {

    public boolean patternMatches(String emailAddress, String regexPattern) {
        return !Pattern.compile(regexPattern)
                .matcher(emailAddress)
                .matches();
    }
}
