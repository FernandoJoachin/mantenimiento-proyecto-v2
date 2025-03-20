package com.example;

import com.example.locproject.models.JavaProgram;
import com.example.locproject.service.LOCAnalyzerService;
import com.example.locproject.service.ProjectScannerService;
import com.example.locproject.utils.ResultPrinterUtil;

import java.io.File;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    System.out.print("Ingrese la ruta del directorio: ");
    String inputPath = scanner.nextLine().trim();
    scanner.close();

    File root = new File(inputPath);
    System.out.println("Ruta ingresada: " + root.getAbsolutePath());

    if (!root.exists()) {
      System.out.println("La ruta especificada no es un directorio válido.");
      return;
    }

    ProjectScannerService projectScanner = new ProjectScannerService(root);

    JavaProgram javaProgram = projectScanner.getJavaProgram();

    LOCAnalyzerService locAnalyzerUtil = new LOCAnalyzerService();

    javaProgram = locAnalyzerUtil.analiyzeLOCJavaProgram(javaProgram);

    ResultPrinterUtil.saveResults(javaProgram);
    
  }
}
