package com.example.locproject.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.List;
import com.example.locproject.models.JavaClass;
import com.example.locproject.models.JavaProgram;

/**
 * Utility class for printing and saving the analysis results of a Java program.
 * It formats the results into a table and writes them to the console and a file.
 */
public class ResultPrinterUtil {

    /**
     * Header for the program column.
     */
    private static final String HEADER_PROGRAM = "Programa";
    
    /**
     * Header for the class column.
     */
    private static final String HEADER_CLASS = "Clase";
    
    /**
     * Header for the methods count column.
     */
    private static final String HEADER_METHODS = "Métodos";
    
    /**
     * Header for the physical lines of code (LOC) column.
     */
    private static final String HEADER_PHYSICAL_LOC = "LOC Físicas";
    
    /**
     * Header for the total physical LOC column.
     */
    private static final String HEADER_TOTAL_LOC = "Total LOC físicas";

    /**
     * Prints the results of the Java program analysis to the console and saves them to a text file.
     *
     * @param nameFile    The name of the file (without extension) to save the results.
     * @param javaProgram The JavaProgram object containing analysis metrics.
     */
    public static void printResults(String nameFile, JavaProgram javaProgram) {
        String result = buildTable(javaProgram);
        System.out.println(result);
        saveResults(nameFile, result);
    }

    /**
     * Saves the formatted results to a text file.
     *
     * @param nameFile The name of the file (without extension).
     * @param data     The formatted results data to be saved.
     */
    private static void saveResults(String nameFile, String data) {
        File output = new File(nameFile + ".txt");
        try {
            output.createNewFile();
            FileWriter filewriter = new FileWriter(output, true);
            filewriter.write(data);
            filewriter.close();
        } catch (IOException exception) {
            Logger.getLogger(ResultPrinterUtil.class.getName()).log(Level.SEVERE, null, exception);
        }
    }

    /**
     * Builds a table representing the analysis results of a Java program.
     *
     * @param javaProgram The JavaProgram object containing the analysis data.
     * @return A formatted string representing the table with headers and data.
     */
    private static String buildTable(JavaProgram javaProgram) {
        List<JavaClass> classes = javaProgram.getClasses();
        List<String> headers = List.of(
            HEADER_PROGRAM, HEADER_CLASS, 
            HEADER_METHODS, HEADER_PHYSICAL_LOC, 
            HEADER_TOTAL_LOC);
        List<List<String>> data = new ArrayList<>();
        boolean isFirstRow = true;

        for (JavaClass javaClass : classes) {
            data.add(List.of(isFirstRow ? javaProgram.getNameProgram() : "", 
                            javaClass.getNameClass(), 
                            String.valueOf(javaClass.getTotalNumberOfMethods()), 
                            String.valueOf(javaClass.getPhysicalLOC()), 
                            ""));
            isFirstRow = false;
        }
        data.add(List.of("", "", "", "", String.valueOf(javaProgram.getTotalPhysicalLOC())));
        return TableFormatterUtil.formatTable(headers, data);
    }
}
