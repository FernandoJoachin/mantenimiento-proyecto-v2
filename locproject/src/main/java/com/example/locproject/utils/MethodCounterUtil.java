package com.example.locproject.utils;

import java.util.regex.Pattern;
import com.example.locproject.constants.JavaRegexConstants;
import com.example.locproject.abstracts.Counter;

/**
 * Utility class for counting the number of methods in a Java source file.
 * Extends the {@link Counter} abstract class to implement method-specific counting logic.
 */
public class MethodCounterUtil extends Counter {

    /**
     * Processes a line of code and increments the counter if it represents a method declaration.
     *
     * @param line The line of code to analyze.
     */
    @Override
    public void count(String line) {
        if (!this.commentValidator.isComment(line) & isMethod(line)) {
            this.counter++;
        } 
    }

    /**
     * Checks if the given line represents a method declaration.
     *
     * @param line The line of code to check.
     * @return {@code true} if the line matches the method declaration pattern, {@code false} otherwise.
     */
    private boolean isMethod(String line) {
        Pattern pattern = Pattern.compile(JavaRegexConstants.METHOD_DECLARATION_REGEX);
        return pattern.matcher(line).find();
    }
}
