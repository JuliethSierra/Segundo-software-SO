/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.math.BigInteger;

/**
 *
 * @author julie
 */
public class Process {
    
    private String name;
    private BigInteger time;
    private boolean isLock;
    private BigInteger priority;

    private boolean isSuspended;
    
    public Process (String name, BigInteger time, boolean isLock, BigInteger priority, boolean isSuspended){
        this.name = name;
        this.time = time;
        this.isLock = isLock;
        this.priority = priority;
        this.isSuspended = isSuspended;
    }

    public Process (Process process){
        this.name = process.getName();
        this.time = process.getTime();
        this.isLock = process.isLock();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigInteger getTime() {
        return time;
    }

    public void setTime(BigInteger time) {
        this.time = time;
    }

    public boolean isLock() {
        return isLock;
    }

    public void setIsLock(boolean isLock) {
        this.isLock = isLock;
    }

    public boolean isSuspended(){
        return isSuspended;
    }

    public BigInteger getPriority() {
        return priority;
    }

    public void setPriority(BigInteger priority) {
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
