package com.example.locproject.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class GoogleJavaFormatUtilTest {

    private GoogleJavaFormatUtil util;
    private File tempFile;

    @BeforeEach
    public void setUp() {
        util = new GoogleJavaFormatUtil();
    }

    @AfterEach
    public void tearDown() throws Exception {
        if (tempFile != null && tempFile.exists()) {
            Files.delete(tempFile.toPath());
        }
    }

    @Test
    public void testValidFormat() throws Exception {
        List<String> lines = new ArrayList<>();
        lines.add("package com.example;");
        lines.add("");
        lines.add("public class TestClass {");
        lines.add("  public void testMethod() {");
        lines.add("    System.out.println(\"Hello World\");");
        lines.add("  }");
        lines.add("}");
        tempFile = File.createTempFile("TestClassValid", ".java");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        }
        boolean isValid = util.isFormatValid(tempFile);
        assertTrue(isValid, "A file with valid format should return true");
    }

    @Test
    public void testBraceError() throws Exception {
        List<String> lines = new ArrayList<>();
        lines.add("package com.example;");
        lines.add("");
        lines.add("if (x > 0) { //"); // incorrect brace position
        lines.add("public class TestClass {"); 
        lines.add("  public void testMethod() {");
        lines.add("    System.out.println(\"Hello World\");");
        lines.add("  }");
        lines.add("}");

        tempFile = File.createTempFile("TestBraceError", ".java");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        }
        boolean isValid = util.isFormatValid(tempFile);
        assertFalse(isValid, "A file with a brace style error should return false");
    }

    @Test
    public void testClassBraceError() throws Exception {
        List<String> lines = new ArrayList<>();
        lines.add("package com.example;");
        lines.add("");
        lines.add("public class TestClass"); // Class declaration without opening brace
        lines.add("{");
        lines.add("  public void testMethod() {");
        lines.add("    System.out.println(\"Hello World\");");
        lines.add("  }");
        lines.add("}");

        tempFile = File.createTempFile("TestClassBraceError", ".java");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        }
        boolean isValid = util.isFormatValid(tempFile);
        assertFalse(isValid, "A file with a class declaration missing an opening brace should return false");
    }

    @Test
    public void testMethodBraceError() throws Exception {
        List<String> lines = new ArrayList<>();
        lines.add("package com.example;");
        lines.add("");
        lines.add("public class TestClass {");
        lines.add("  public void testMethod() "); // Method declaration without opening brace
        lines.add("  {");
        lines.add("    System.out.println(\"Hello World\");");
        lines.add("  }");
        lines.add("}");

        tempFile = File.createTempFile("TestMethodBraceError", ".java");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        }
        boolean isValid = util.isFormatValid(tempFile);
        assertFalse(isValid, "A file with a method declaration missing an opening brace should return false");
    }

    @Test
    public void testLineLengthError() throws Exception {
        StringBuilder longLine = new StringBuilder();
        for (int i = 0; i < 200; i++) {
            longLine.append("a");
        }

        List<String> lines = new ArrayList<>();
        lines.add("package com.example;");
        lines.add("");
        lines.add(longLine.toString()); // Use the long line in the file.
        lines.add("public class TestClass {");
        lines.add("  public void testMethod() {");
        lines.add("    System.out.println(\"Hello World\");");
        lines.add("  }");
        lines.add("}");

        tempFile = File.createTempFile("TestLineLengthError", ".java");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        }
        boolean isValid = util.isFormatValid(tempFile);
        assertFalse(isValid, "A file with a line length error should return false");
    }

    @Test
    public void testIndentationError() throws Exception {
        List<String> lines = new ArrayList<>();
        lines.add("package com.example;");
        lines.add("");
        lines.add("public class TestClass {");
        lines.add("   System.out.println(\"Indentation error\");"); // Line with 3 leading spaces (invalid indentation, odd number)
        lines.add("  public void testMethod() {");
        lines.add("    System.out.println(\"Hello World\");");
        lines.add("  }");
        lines.add("}");

        tempFile = File.createTempFile("TestIndentationError", ".java");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        }
        boolean isValid = util.isFormatValid(tempFile);
        assertFalse(isValid, "A file with an indentation error should return false");
    }
}
