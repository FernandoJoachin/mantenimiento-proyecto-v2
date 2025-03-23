package com.example.locproject.utils;

import com.example.locproject.abstracts.Counter;

public class PhysicalLineCounterUtil extends Counter {
    @Override
    public void count(String line) {
        if (!this.commentValidator.isComment(line) & !line.trim().isEmpty()) {
            this.counter++;
        } 
    }
}