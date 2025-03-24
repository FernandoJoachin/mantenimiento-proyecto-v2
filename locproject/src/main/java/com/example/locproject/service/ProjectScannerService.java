package com.example.locproject.service;

import java.io.File;
import java.util.ArrayList;
import com.example.locproject.models.JavaClass;
import com.example.locproject.models.JavaProgram;
import com.example.locproject.utils.GoogleJavaFormatUtil;

/**
 * Service class responsible for scanning a directory to identify Java files and create a Java program representation.
 */
public class ProjectScannerService {

  /**
   * Utility to validate Java file formatting.
   */
  private GoogleJavaFormatUtil formatter;
  
  /**
   * Represents the Java program, containing the list of Java classes and the source directory.
   */
  private JavaProgram javaProgram;

  /**
   * Constructs a ProjectScannerService instance.
   * 
   * @param directory The directory containing Java files.
   * @param formatter Utility to validate Java file formatting.
   */
  public ProjectScannerService(File directory, GoogleJavaFormatUtil formatter) {
    this.javaProgram = new JavaProgram(new ArrayList<JavaClass>(), directory);
    this.formatter = formatter;
  }

  /**
   * Recursively scans a directory to find Java files and add them to the program.
   * 
   * @param path The directory or file to scan.
   */
  private void scanDirectory(File path) {
    if (path.isDirectory()) {
      File[] files = path.listFiles();
      if (files != null) {
        for (File file : files) {
          scanDirectory(file);
        }
      }
    } else {
      if (path.getName().endsWith(".java") && formatter.isFormatValid(path)) {
        JavaClass javaClass = new JavaClass(path);
        this.javaProgram.addClasses(javaClass);
      }
    }
  }

  /**
   * Retrieves the Java program representation after scanning the directory.
   * 
   * @return The JavaProgram object with identified Java classes.
   */
  public JavaProgram getJavaProgram() {
    scanDirectory(this.javaProgram.getDirectory());
    return this.javaProgram;    
  }
}
