package com.example.locproject.service;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import com.example.locproject.models.JavaClass;
import com.example.locproject.models.JavaProgram;
import com.example.locproject.utils.MethodCounterUtil;
import com.example.locproject.utils.PhysicalLineCounterUtil;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

public class LOCAnalyzerServiceTest {

  @TempDir
  Path tempDir;

  private PhysicalLineCounterUtil physicalLineCounter;
  private MethodCounterUtil methodCounter;
  private LOCAnalyzerService locAnalyzerService;

  @BeforeEach
  public void setUp() {
    physicalLineCounter = new PhysicalLineCounterUtil();
    methodCounter = new MethodCounterUtil();
    locAnalyzerService = new LOCAnalyzerService(physicalLineCounter, methodCounter);
  }

  @Test
  public void testAnaliyzeLOCJavaProgram() throws IOException {
    File javaFile1 = File.createTempFile("Test1", ".java");
    try (FileWriter writer = new FileWriter(javaFile1)) {
      writer.write(String.join(System.lineSeparator(), List.of(
          "package com.example.files;",
          "",
          "public class Test1 {",
          "    // Single-line comment",
          "",
          "    /* ",
          "     * Multi-line comment",
          "     * This should not be counted as a logical line",
          "     */",
          "",
          "    public static void main(String[] args) {",
          "        System.out.println(\"Hello, world!\");",
          "    }",
          "",
          "    public void methodOne() {",
          "        int x = 10;",
          "        int y = 20;",
          "        System.out.println(x + y);",
          "    }",
          "",
          "    public int methodTwo(int a, int b) {",
          "        return a + b;",
          "    }",
          "}")));
    }

    // Crear archivo Test2.java
    File javaFile2 = File.createTempFile("Test2", ".java");
    try (FileWriter writer = new FileWriter(javaFile2)) {
      writer.write(String.join(System.lineSeparator(), List.of(
          "package com.example.files;",
          "",
          "public class Test2 {",
          "    public void methodOne() {",
          "        int x = 10;",
          "        int y = 20;",
          "        System.out.println(x + y);",
          "    }",
          "",
          "    public int methodTwo(int a, int b) {",
          "        return a + b;",
          "    }",
          "}")));
    }

    JavaClass javaClass1 = new JavaClass(javaFile1);
    JavaClass javaClass2 = new JavaClass(javaFile2);
    List<JavaClass> listJavaClass = List.of(javaClass1, javaClass2);
    JavaProgram javaProgram = new JavaProgram(new ArrayList<>(listJavaClass), tempDir.toFile());

    JavaProgram result = locAnalyzerService.analiyzeLOCJavaProgram(javaProgram);

    assertNotNull(result, "The Java program should not be null");

    assertEquals(
      25, 
      result.getTotalPhysicalLOC(), 
      "The total physical lines of code do not match"
    );

    assertEquals(
      14, 
      result.getClasses().get(0).getPhysicalLOC(), 
      "The LOC of Test1.java does not match"
    );
    assertEquals(
      3, 
      result.getClasses().get(0).getTotalNumberOfMethods(),
     "The number of methods in Test1.java does not match"
    );

    assertEquals(
      11, 
      result.getClasses().get(1).getPhysicalLOC(),
      "The LOC of Test2.java does not match"
    );
    assertEquals(
      2, 
      result.getClasses().get(1).getTotalNumberOfMethods(), 
      "The number of methods in Test2.java does not match"
    );
  }
}