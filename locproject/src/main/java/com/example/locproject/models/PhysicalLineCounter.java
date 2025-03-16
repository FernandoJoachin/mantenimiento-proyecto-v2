package com.example.locproject.models;

import com.example.locproject.interfaces.Counter;

public class PhysicalLineCounter extends Counter {
    @Override
    public void count(String line) {
        if (!this.commentValidator.isComment(line) & !line.trim().isEmpty()) {
            this.counter++;
        } 
    }
}