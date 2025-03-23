package com.example.locproject.service;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.example.locproject.models.JavaClass;
import com.example.locproject.models.JavaProgram;
import com.example.locproject.utils.MethodCounterUtil;
import com.example.locproject.utils.PhysicalLineCounterUtil;

public class LOCAnalyzerService {
  
  private PhysicalLineCounterUtil physicalLineCounter;
  private MethodCounterUtil methodCounter;

  public LOCAnalyzerService(
    PhysicalLineCounterUtil physicalLineCounter,
    MethodCounterUtil methodCounter
  ) {
    this.physicalLineCounter = physicalLineCounter;
    this.methodCounter = methodCounter;
  }

  public JavaProgram analiyzeLOCJavaProgram(JavaProgram javaProgram) {
    JavaProgram programToBeAnalyzed = javaProgram;

    for (JavaClass javaClass : programToBeAnalyzed.getClasses()) {
      this.calculateMetricsJavaClass(javaClass);
    }

    int totalLOCProgram = countSLOCProgram(programToBeAnalyzed);
    programToBeAnalyzed.setTotalPhysicalLOC(totalLOCProgram);

    return programToBeAnalyzed;
  }

  private JavaClass calculateMetricsJavaClass(JavaClass javaClass) {
    JavaClass classToBeAnalyzed = javaClass;

    try (Scanner scanner = new Scanner(classToBeAnalyzed.getJavaFile())) {
      while (scanner.hasNext()) {
        String currentLine = scanner.nextLine().trim();
        this.physicalLineCounter.count(currentLine);
        this.methodCounter.count(currentLine);
      }
      scanner.close();

      classToBeAnalyzed.setPhysicalLOC(physicalLineCounter.getCount());
      classToBeAnalyzed.setTotalNumberOfMethods(methodCounter.getCount());
    } catch (FileNotFoundException e) {
      Logger.getLogger(LOCAnalyzerService.class.getName()).log(Level.SEVERE, null, e);
    }
    
    this.physicalLineCounter.resetCount();
    this.methodCounter.resetCount();

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
