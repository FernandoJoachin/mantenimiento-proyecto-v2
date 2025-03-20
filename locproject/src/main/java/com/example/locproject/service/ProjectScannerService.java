package com.example.locproject.service;

import java.io.File;
import java.util.ArrayList;

import com.example.locproject.models.JavaClass;
import com.example.locproject.models.JavaProgram;
import com.example.locproject.utils.GoogleJavaFormatUtil;

public class ProjectScannerService {

  private ArrayList<JavaClass> javaClasses;
  private File directory;

  public ProjectScannerService(File directory) {
    this.javaClasses = new ArrayList<>();
    this.directory = directory;
  }

  public JavaProgram getJavaProgram() {
    scanDirectory(this.directory);
    return new JavaProgram(this.javaClasses, this.directory.getName());
  }

  private void scanDirectory(File directory) {

    GoogleJavaFormatUtil formatter = new GoogleJavaFormatUtil();

    if (directory.isDirectory()) {
      File[] files = directory.listFiles();
      if (files != null) {
        for (File file : files) {
          scanDirectory(file);
        }
      }
    } else {
      if (directory.getName().endsWith(".java") && formatter.isFormatValid(directory)) {
        JavaClass javaClass = new JavaClass(directory);
        this.javaClasses.add(javaClass);
      }
    }
  }
}
