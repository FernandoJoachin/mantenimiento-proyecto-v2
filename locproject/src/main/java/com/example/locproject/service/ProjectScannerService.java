package com.example.locproject.service;

import java.io.File;
import java.util.ArrayList;

import com.example.locproject.models.JavaClass;
import com.example.locproject.models.JavaProgram;
import com.example.locproject.utils.GoogleJavaFormatUtil;

public class ProjectScannerService {

  private GoogleJavaFormatUtil formatter;
  private JavaProgram javaProgram;

  public ProjectScannerService(String namePrograme, GoogleJavaFormatUtil formatter) {
    this.javaProgram = new JavaProgram(new ArrayList<JavaClass>(), namePrograme);
    this.formatter = formatter;
  }

  public void scanDirectory(File path) {
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

  public JavaProgram getJavaProgram() {
    return this.javaProgram;    
  }
}
