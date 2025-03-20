package com.example.locproject.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.example.locproject.models.JavaClass;
import com.example.locproject.models.JavaProgram;
import com.example.locproject.service.LOCAnalyzerService;

public class ResultPrinterUtil {

    public static void saveResults(JavaProgram javaProgram) {
        File output = new File("output.txt");
        String result = getProgramResult(javaProgram.getNameProgram(), javaProgram.getTotalPhysicalLOC());

        try {
            output.createNewFile();
            FileWriter filewriter = new FileWriter(output);

            for (JavaClass javaClass : javaProgram.getClasses()) {
                result += getClassResult(javaClass);
            }
            filewriter.write(result);
            filewriter.close();
        } catch (IOException exception) {
            Logger.getLogger(LOCAnalyzerService.class.getName()).log(Level.SEVERE, null, exception);
        }
    }

    private static String getProgramResult(String nameProgram, int totalLOCProgram) {
        return "Programa: " + 
            nameProgram + 
            "\nTotal de LOC fisicas del programa: " +
            totalLOCProgram + 
            "\n";
    }

    private static String getClassResult(JavaClass javaClass) {
        return "\nClase: " + 
            javaClass.getNameClass() +
            "\nTotal de metodos: " +
            javaClass.getTotalNumberOfMethods() +
            "\nLOC fisicas: " + 
            javaClass.getPhysicalLOC() + 
            "\n";
    }
}
