package com.example.locproject.interfaces;

import com.example.locproject.validators.CommentValidator;

/**
 * This abstract class defines the contract for counting of code.
 * It includes a method to count lines based on specific implementation logic.
 */
public abstract class Counter {
    protected int counter = 0;
    protected CommentValidator commentValidator = new CommentValidator();

    public abstract void count(String line);

    public int getCount() {
        return this.counter;
    }
}