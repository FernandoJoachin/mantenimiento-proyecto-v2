package com.example.locproject.abstracts;

import com.example.locproject.validators.CommentValidator;

/**
 * Abstract class that defines the contract for counting lines of code.
 * Implementing classes must provide specific logic for counting lines.
 */
public abstract class Counter {
    
    /**
     * Stores the count of lines.
     */
    protected int counter = 0;
    
    /**
     * Validator instance to handle comment-related logic.
     */
    protected CommentValidator commentValidator = new CommentValidator();

    /**
     * Abstract method to count lines based on a given input line.
     * Implementing classes should define their own counting logic.
     * 
     * @param line The input line to be analyzed.
     */
    public abstract void count(String line);

    /**
     * Gets the current count of lines.
     * 
     * @return The number of counted lines.
     */
    public int getCount() {
        return this.counter;
    }

    /**
     * Resets the line count to zero.
     */
    public void resetCount(){
        this.counter = 0;
    }
}
