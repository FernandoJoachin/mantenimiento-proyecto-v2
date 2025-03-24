package com.example.locproject.utils;

import java.util.regex.Pattern;

import com.example.locproject.constants.JavaRegexConstants;
import com.example.locproject.abstracts.Counter;

public class MethodCounterUtil extends Counter {

    @Override
    public void count(String line) {
        if (!this.commentValidator.isComment(line) & isMethod(line)) {
            this.counter++;
        } 
    }

    private boolean isMethod(String line) {
        Pattern pattern = Pattern.compile(JavaRegexConstants.METHOD_DECLARATION_REGEX);
        if (pattern.matcher(line).find()) {
            return true;
        }

        return false;
    }
}
