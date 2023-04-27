package com.librarymanagement.utils;

import java.util.function.Predicate;

import org.springframework.stereotype.Component;

@Component
public class EmailValidator implements Predicate<String> {

    @Override
    public boolean test(String s) {
        // Loginc for email validator
        if (s.contains("@gmail.com")) {
            return true;
        }
        return false;
    }

}
