package com.example.locproject.models;

import java.util.ArrayList;

public class JavaProgram {
    private ArrayList<JavaClass> classes;
    private int totalPhysicalLOC;
    private String nameProgram;

    public JavaProgram(ArrayList<JavaClass> classes, String nameProgram) {
        this.classes = classes;
        this.nameProgram = nameProgram;
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

    public String getNameProgram() {
        return nameProgram;
    }

    public void setNameProgram(String nameProgram) {
        this.nameProgram = nameProgram;
    }
    
}
