package com.example.locproject.models;

import java.io.File;

/**
 * Represents a Java class file, storing the number of physical lines of code
 * and the number of methods present in the class.
 */
public class JavaClass {
    
    /**
     * The number of physical lines of code in the class.
     */
    private int physicalLOC;
    
    /**
     * The total number of methods in the class.
     */
    private int totalNumberOfMethods;
    
    /**
     * The Java file associated with this class.
     */
    private File javaFile;
    
    /**
     * The name of the Java class.
     */
    private String nameClass;

    /**
     * Constructs a JavaClass instance based on a given Java file.
     * 
     * @param javaFile The Java file representing the class.
     */
    public JavaClass(File javaFile) {
        this.javaFile = javaFile;
        this.nameClass = javaFile.getName();
        this.totalNumberOfMethods = 0;
    }

    /**
     * Gets the number of physical lines of code.
     * 
     * @return The physical lines of code.
     */
    public int getPhysicalLOC() {
        return physicalLOC;
    }

    /**
     * Sets the number of physical lines of code.
     * 
     * @param physicalLOC The physical lines of code to set.
     */
    public void setPhysicalLOC(int physicalLOC) {
        this.physicalLOC = physicalLOC;
    }

    /**
     * Gets the total number of methods in the class.
     * 
     * @return The total number of methods.
     */
    public int getTotalNumberOfMethods() {
        return totalNumberOfMethods;
    }

    /**
     * Sets the total number of methods in the class.
     * 
     * @param totalNumberOfMethods The total number of methods to set.
     */
    public void setTotalNumberOfMethods(int totalNumberOfMethods) {
        this.totalNumberOfMethods = totalNumberOfMethods;
    }

    /**
     * Gets the Java file associated with this class.
     * 
     * @return The Java file.
     */
    public File getJavaFile() {
        return javaFile;
    }

    /**
     * Sets the Java file associated with this class.
     * 
     * @param javaFile The Java file to set.
     */
    public void setJavaFile(File javaFile) {
        this.javaFile = javaFile;
    }

    /**
     * Gets the name of the Java class.
     * 
     * @return The class name.
     */
    public String getNameClass() {
        return nameClass;
    }

    /**
     * Sets the name of the Java class.
     * 
     * @param nameClass The class name to set.
     */
    public void setNameClass(String nameClass) {
        this.nameClass = nameClass;
    }
}
