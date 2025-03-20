package com.example.locproject.models;

import java.io.File;

public class JavaClass {
    private int physicalLOC;
    private int totalNumberOfMethods;
    private File javaFile;
    private String nameClass;

    public JavaClass(File javaFile) {
        this.javaFile = javaFile;
        this.nameClass = javaFile.getName();
        this.totalNumberOfMethods = 0;
    }

    public int getPhysicalLOC() {
        return physicalLOC;
    }

    public void setPhysicalLOC(int physicalLOC) {
        this.physicalLOC = physicalLOC;
    }

    public int getTotalNumberOfMethods() {
        return totalNumberOfMethods;
    }

    public void setTotalNumberOfMethods(int totalNumberOfMethods) {
        this.totalNumberOfMethods = totalNumberOfMethods;
    }

    public File getJavaFile() {
        return javaFile;
    }

    public void setJavaFile(File javaFile) {
        this.javaFile = javaFile;
    }

    public String getNameClass() {
        return nameClass;
    }

    public void setNameClass(String nameClass) {
        this.nameClass = nameClass;
    }

}
