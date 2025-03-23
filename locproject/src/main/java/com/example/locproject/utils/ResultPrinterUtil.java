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

public class ResultPrinterUtil {

    private static final String HEADER_PROGRAM = "Programa";
    private static final String HEADER_CLASS = "Clase";
    private static final String HEADER_METHODS = "Métodos";
    private static final String HEADER_PHYSICAL_LOC = "LOC Físicas";
    private static final String HEADER_TOTAL_LOC = "Total LOC físicas";

    public static void printResults(String nameFile, JavaProgram javaProgram) {
        String result = buildTable(javaProgram);
        System.out.println(result);
        saveResults(nameFile, result);
    }

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

    private static String buildTable(JavaProgram javaProgram) {
        List<JavaClass> classes = javaProgram.getClasses();

        List<String> headers = List.of(HEADER_PROGRAM, HEADER_CLASS, HEADER_METHODS, HEADER_PHYSICAL_LOC, HEADER_TOTAL_LOC);

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

        return TableFormatter.formatTable(headers, data);
    }
}
