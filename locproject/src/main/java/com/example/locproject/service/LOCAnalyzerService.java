package com.example.locproject.service;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.example.locproject.models.JavaClass;
import com.example.locproject.models.JavaProgram;
import com.example.locproject.utils.MethodCounterUtil;
import com.example.locproject.utils.PhysicalLineCounterUtil;

/**
 * Service class responsible for analyzing the lines of code (LOC) in a Java program.
 * It counts the number of physical LOC and methods in each Java class.
 */
public class LOCAnalyzerService {
  
  /**
   * Utility to count physical lines of code.
   */
  private PhysicalLineCounterUtil physicalLineCounter;
  
  /**
   * Utility to count methods.
   */
  private MethodCounterUtil methodCounter;

  /**
   * Constructs an LOCAnalyzerService instance.
   * 
   * @param physicalLineCounter Utility to count physical lines of code.
   * @param methodCounter       Utility to count methods.
   */
  public LOCAnalyzerService(PhysicalLineCounterUtil physicalLineCounter, MethodCounterUtil methodCounter) {
    this.physicalLineCounter = physicalLineCounter;
    this.methodCounter = methodCounter;
  }

  /**
   * Analyzes the lines of code in a given Java program.
   * 
   * @param javaProgram The Java program to analyze.
   * @return The analyzed Java program with updated LOC metrics.
   */
  public JavaProgram analiyzeLOCJavaProgram(JavaProgram javaProgram) {
    JavaProgram programToBeAnalyzed = javaProgram;

    for (JavaClass javaClass : programToBeAnalyzed.getClasses()) {
      this.calculateMetricsJavaClass(javaClass);
    }

    int totalLOCProgram = countSLOCProgram(programToBeAnalyzed);
    programToBeAnalyzed.setTotalPhysicalLOC(totalLOCProgram);

    return programToBeAnalyzed;
  }

  /**
   * Calculates metrics for a specific Java class, including physical LOC and method count.
   * 
   * @param javaClass The Java class to analyze.
   * @return The analyzed Java class with updated metrics.
   */
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

  /**
   * Counts the total number of physical lines of code in a Java program.
   * 
   * @param javaProgram The Java program to analyze.
   * @return The total physical LOC of the program.
   */
  private int countSLOCProgram(JavaProgram javaProgram) {
    JavaProgram project = javaProgram;
    int totalLOCProgram = 0;

    for (JavaClass javaClass : project.getClasses()) {
      totalLOCProgram += javaClass.getPhysicalLOC();
    }

    return totalLOCProgram;
  }
}
