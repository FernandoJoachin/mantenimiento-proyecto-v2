package com.example.locproject.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MethodCounterUtilTest {

    private MethodCounterUtil counter = new MethodCounterUtil();

    @Test
    public void testCountMethods() {
      
        String[] lines = {
            "public class Example {",  
            "    // This is a comment",  
            "    public Example() {", 
            "        // Some code",
            "    }",  
            "    public void exampleMethod() {", 
            "        // Some code",
            "    }",  
            "    public int anotherMethod(int x) {", 
            "        return x;", 
            "    }", 
            "    // Another comment",
            "",  
            "}"  
        };

        // Count methods in the lines
        for (String line : lines) {
            counter.count(line);
        }

        
        assertEquals(3, counter.getCount(), "Expected 3 method declarations.");
    }
}
