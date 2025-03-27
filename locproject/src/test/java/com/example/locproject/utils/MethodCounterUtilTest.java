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
            "    public void exampleMethod() {", 
            "        // Some code",
            "    }",  // End of method
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

        
        assertEquals(2, counter.getCount(), "Expected 2 method declarations.");
    }
}
