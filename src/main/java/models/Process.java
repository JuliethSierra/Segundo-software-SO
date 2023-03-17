/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author julie
 */
public class Process {
    
    private String name;
    private double time;
    private boolean isLock;
    private int priority;

    private boolean isSuspended;
    
    public Process (String name, double time, boolean isLock, int priority, boolean isSuspended){
        this.name = name;
        this.time = time;
        this.isLock = isLock;
        this.priority = priority;
        this.isSuspended = isSuspended;
    }

    public Process (Process process){
        this.name = process.getName();
        this.time = process.getTime();
        this.isLock = process.isIsLock();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public boolean isIsLock() {
        return isLock;
    }

    public void setIsLock(boolean isLock) {
        this.isLock = isLock;
    }

    public boolean isSuspended(){
        return isSuspended;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "Process{" +
                "name='" + name + '\'' +
                ", time=" + time +
                ", isLock=" + isLock +
                '}';
    }
}
