package com.example.locproject.models;

import java.io.File;
import java.util.ArrayList;

/**
 * Represents a Java program containing multiple Java classes.
 * It provides methods to manage the program's classes and calculate total physical lines of code.
 */
public class JavaProgram {
    
    /**
     * The list of Java classes that make up the program.
     */
    private ArrayList<JavaClass> classes;
    
    /**
     * The total number of physical lines of code in the program.
     */
    private int totalPhysicalLOC;
    
    /**
     * The directory where the Java source files of the program are located.
     */
    private File directory;

    /**
     * Constructs a JavaProgram instance.
     * 
     * @param classes   A list of JavaClass objects in the program.
     * @param directory The directory containing the Java source files.
     */
    public JavaProgram(ArrayList<JavaClass> classes, File directory) {
        this.classes = classes;
        this.directory = directory;
    }

    /**
     * Gets the list of Java classes in the program.
     * 
     * @return A list of JavaClass objects.
     */
    public ArrayList<JavaClass> getClasses() {
        return classes;
    }

    /**
     * Sets the list of Java classes in the program.
     * 
     * @param classes A new list of JavaClass objects.
     */
    public void setClasses(ArrayList<JavaClass> classes) {
        this.classes = classes;
    }

    /**
     * Adds a Java class to the program.
     * 
     * @param classes The JavaClass object to be added.
     */
    public void addClasses(JavaClass classes){
        this.classes.add(classes);
    }

    /**
     * Gets the total number of physical lines of code in the program.
     * 
     * @return The total physical LOC.
     */
    public int getTotalPhysicalLOC() {
        return totalPhysicalLOC;
    }

    /**
     * Sets the total number of physical lines of code in the program.
     * 
     * @param totalPhysicalLOC The total physical LOC.
     */
    public void setTotalPhysicalLOC(int totalPhysicalLOC) {
        this.totalPhysicalLOC = totalPhysicalLOC;
    }

    /**
     * Gets the directory containing the Java program files.
     * 
     * @return The directory as a File object.
     */
    public File getDirectory() {
        return this.directory;
    }

    /**
     * Sets the directory containing the Java program files.
     * 
     * @param directory The new directory as a File object.
     */
    public void setDirectroy(File directory) {
        this.directory = directory;
    }

    /**
     * Gets the name of the Java program based on the directory name.
     * 
     * @return The name of the program.
     */
    public String getNameProgram() {
        return directory.getName();
    }    
}
