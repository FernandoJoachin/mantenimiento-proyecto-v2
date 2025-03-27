package com.example.locproject.utils;

import com.example.locproject.abstracts.Counter;

/**
 * Utility class for counting the number of physical (non-comment, non-empty) lines in a Java source file.
 * Extends the {@link Counter} abstract class to implement physical line counting logic.
 */
public class PhysicalLineCounterUtil extends Counter {

    /**
     * Counts a physical line of code excluding comments and empty lines.
     *
     * @param line The line of code to analyze.
     */
    @Override
    public void count(String line) {
        if (!this.commentValidator.isComment(line) & !line.trim().isEmpty()) {
            this.counter++;
        } 
    }
}
