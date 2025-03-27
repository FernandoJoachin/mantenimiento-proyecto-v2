package com.example.locproject.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PhysicalLineCounterUtilTest {

    private PhysicalLineCounterUtil lineCounter;

    @BeforeEach
    public void setUp() {
        lineCounter = new PhysicalLineCounterUtil();
    }

   
    @Test
    public void testNoPhysicalLines() {
        List<String> lines = Arrays.asList(
            "package com.example;",
            "import java.util.List;", 
            "", 
            "// Este es un comentario", 
            "@Deprecated", 
            "int x = 0" 
        );

        for (String line : lines) {
            lineCounter.count(line);
        }

     
        assertEquals(3, lineCounter.getCount(), "Se esperaban 3 líneas físicas.");
    }

    
    @Test
    public void testValidPhysicalLines() {
        List<String> lines = Arrays.asList(
            "public class TestClass {", 
            "    int a = 5;", 
            "    // Esto es un comentario", 
            "}", 
            "    // Otro comentario" 
        );

        for (String line : lines) {
            lineCounter.count(line);
        }

        assertEquals(3, lineCounter.getCount(), "Se esperaban 3 líneas físicas.");
    }


    @Test
    public void testEmptyLineShouldNotIncrementCounter() {
        List<String> lines = Arrays.asList(
            "public class TestClass {", 
            "", 
            "    int a = 5;", 
            "}" 
        );

        for (String line : lines) {
            lineCounter.count(line);
        }


        assertEquals(3, lineCounter.getCount(), "Se esperaban 3 líneas físicas.");
    }


    @Test
    public void testCommentShouldNotIncrementCounter() {
        List<String> lines = Arrays.asList(
            "public class TestClass {", 
            "// Esto es un comentario", 
            "    int a = 5;", 
            "// Otro comentario" 
        );

        for (String line : lines) {
            lineCounter.count(line);
        }


        assertEquals(2, lineCounter.getCount(), "Se esperaban 2 líneas físicas.");
    }
}
