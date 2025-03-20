package com.example.locproject.service;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.example.locproject.interfaces.Counter;
import com.example.locproject.models.JavaClass;
import com.example.locproject.models.JavaProgram;
import com.example.locproject.utils.PhysicalLineCounterUtil;

public class LOCAnalyzerService {

  public JavaProgram analiyzeLOCJavaProgram(JavaProgram javaProgram) {
    for (JavaClass javaClass : javaProgram.getClasses()) {
      this.calculateMetricsJavaClass(javaClass);
    }

    int totalLOCProgram = countSLOCProgram(javaProgram);
    javaProgram.setTotalPhysicalLOC(totalLOCProgram);

    return javaProgram;
  }

  private JavaClass calculateMetricsJavaClass(JavaClass javaClass) {
    JavaClass classToBeAnalyzed = javaClass;
    Counter physicalLineCounter = new PhysicalLineCounterUtil();

    try (Scanner scanner = new Scanner(classToBeAnalyzed.getJavaFile())) {
      while (scanner.hasNext()) {
        String currentLine = scanner.nextLine().trim();
        physicalLineCounter.count(currentLine);
      }
      scanner.close();

      classToBeAnalyzed.setPhysicalLOC(physicalLineCounter.getCount());
    } catch (FileNotFoundException e) {
      Logger.getLogger(LOCAnalyzerService.class.getName()).log(Level.SEVERE, null, e);
    }

    return classToBeAnalyzed;
  }

  private int countSLOCProgram(JavaProgram javaProgram) {
    JavaProgram project = javaProgram;
    int totalLOCProgram = 0;

    for (JavaClass javaClass : project.getClasses()) {
      totalLOCProgram += javaClass.getPhysicalLOC();
    }

    return totalLOCProgram;
  }
}
