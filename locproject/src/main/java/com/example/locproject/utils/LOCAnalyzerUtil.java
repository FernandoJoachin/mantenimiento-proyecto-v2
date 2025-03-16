package com.example.locproject.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.example.locproject.interfaces.Counter;
import com.example.locproject.models.LogicalLineCounter;
import com.example.locproject.models.PhysicalLineCounter;

public class LOCAnalyzerUtil {

  private static int totalLinesProject = 0;
  private static String result = "";

  public void countLinesOfCode(File file) {
    try {
      int totalLines = 0;
      Scanner scanner = new Scanner(file);
      Counter logicalLineCounter = new LogicalLineCounter();
      Counter physicalLineCounter = new PhysicalLineCounter();

      while (scanner.hasNext()) {
        totalLines++;
        String currentLine = scanner.nextLine().trim();
        logicalLineCounter.count(currentLine);
        physicalLineCounter.count(currentLine);
      }
      scanner.close();

      totalLinesProject += totalLines;
      int logicalLine = logicalLineCounter.getCount();
      int physicalLine = physicalLineCounter.getCount();
      result +=
          "\nProgram: "
              + file.getName()
              + "\nLogical Lines = "
              + logicalLine
              + " | Physical Lines = "
              + physicalLine
              + " | Total Lines = "
              + totalLines
              + "\n----------------------------------------------------------";

    } catch (IOException ex) {
      Logger.getLogger(LOCAnalyzerUtil.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public void saveResults() {
    File output = new File("output.txt");
    try {
      output.createNewFile();
      FileWriter filewriter = new FileWriter(output);
      filewriter.write(result + "\nTotal LOC in project= " + totalLinesProject);
      filewriter.close();
    } catch (IOException exception) {
      Logger.getLogger(LOCAnalyzerUtil.class.getName()).log(Level.SEVERE, null, exception);
    }
  }

  public String getResult() {
    return result;
  }

  public void reset() {
    totalLinesProject = 0;
    result = "";
  }
}
