package com.example.locproject.models;

import java.io.File;
import java.util.ArrayList;

public class JavaProgram {
    private ArrayList<JavaClass> classes;
    private int totalPhysicalLOC;
    private File directory;

    public JavaProgram(ArrayList<JavaClass> classes, File directory) {
        this.classes = classes;
        this.directory = directory;
    }

    public ArrayList<JavaClass> getClasses() {
        return classes;
    }

    public void setClasses(ArrayList<JavaClass> classes) {
        this.classes = classes;
    }

    public void addClasses(JavaClass classes){
        this.classes.add(classes);
    }

    public int getTotalPhysicalLOC() {
        return totalPhysicalLOC;
    }

    public void setTotalPhysicalLOC(int totalPhysicalLOC) {
        this.totalPhysicalLOC = totalPhysicalLOC;
    }

    public File getDirectory() {
        return this.directory;
    }

    public void setDirectroy(File directory) {
        this.directory = directory;
    }

    public String getNameProgram() {
        return directory.getName();
    }    
}
