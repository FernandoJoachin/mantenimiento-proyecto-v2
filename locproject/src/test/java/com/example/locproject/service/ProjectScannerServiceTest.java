package com.example.locproject.service;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import com.example.locproject.models.JavaProgram;
import com.example.locproject.utils.GoogleJavaFormatUtil;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

public class ProjectScannerServiceTest {
  @TempDir
  Path tempDir;

  private GoogleJavaFormatUtil formatter;
  private ProjectScannerService projectScannerService;

  @BeforeEach
  public void setUp() {
    formatter = new GoogleJavaFormatUtil();
    projectScannerService = new ProjectScannerService(tempDir.toFile(), formatter);
  }

  @Test
  public void testGetJavaProgramWithValidJavaFiles() throws IOException {
    Files.createFile(tempDir.resolve("Test1.java")).toFile();
    Files.createFile(tempDir.resolve("Test2.java")).toFile();

    Files.createFile(tempDir.resolve("Test.txt"));

    JavaProgram result = projectScannerService.getJavaProgram();

    assertEquals(
      2, 
      result.getClasses().size(), 
      "The number of classes in JavaProgram should be 2"
    );
    assertEquals(
      "Test1.java", 
      result.getClasses().get(0).getNameClass(), 
      "The name of the first class should be 'Test1.java'"
    );
    assertEquals(
      "Test2.java", 
      result.getClasses().get(1).getNameClass(), 
      "The name of the second class should be 'Test2.java'"
    );
    assertEquals(
      tempDir.toFile(), 
      result.getDirectory(), 
      "The directory of JavaProgram should match the created temporary directory"
    );
  }
}