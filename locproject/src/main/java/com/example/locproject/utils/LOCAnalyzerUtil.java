package com.example.locproject.utils;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.example.locproject.interfaces.Counter;
import com.example.locproject.models.JavaClass;
import com.example.locproject.models.JavaProgram;
import com.example.locproject.models.PhysicalLineCounter;

public class LOCAnalyzerUtil {

  public JavaProgram analiyzeLOCJavaProgram(JavaProgram javaProgram) {
    JavaProgram project = javaProgram;
    
    for (JavaClass javaClass : project.getClasses()) {
      this.countPhysicalLOC(javaClass);
    }

    int totalLOCProgram = countPhysicalLOCProgram(project);

    project.setTotalPhysicalLOC(totalLOCProgram);

    return project;
  }

  private JavaClass countPhysicalLOC(JavaClass javaClass) {
    JavaClass classToBeAnalyzed = javaClass;
    Counter physicalLineCounter = new PhysicalLineCounter();

    try (Scanner scanner = new Scanner(classToBeAnalyzed.getJavaFile())) {
      while (scanner.hasNext()) {
        String currentLine = scanner.nextLine().trim();
        physicalLineCounter.count(currentLine);
      }
      scanner.close();

      classToBeAnalyzed.setPhysicalLOC(physicalLineCounter.getCount());
    } catch (FileNotFoundException e) {
      Logger.getLogger(LOCAnalyzerUtil.class.getName()).log(Level.SEVERE, null, e);
    }

    return classToBeAnalyzed;
  }

  private int countPhysicalLOCProgram(JavaProgram javaProgram) {
    JavaProgram project = javaProgram;
    int totalLOCProgram = 0;

    for (JavaClass javaClass : project.getClasses()) {
      totalLOCProgram += javaClass.getPhysicalLOC();
    }

    return totalLOCProgram;
  }

}
