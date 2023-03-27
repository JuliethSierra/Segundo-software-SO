/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.io.ObjectStreamException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author julie
 */
public class ProcessManager {

    private final int PROCESS_TIME = 5;

    private List<Process> currentList, queueList, readyList, dispatchList, executionList, finishedList, blockList, expirationList, wakeUpList, destroyedList, suspendedList, restartedList;
    private ArrayList<ArrayList<Process>> relationList;

    public ProcessManager() {
        this.queueList = new ArrayList<Process>();
        this.currentList = new ArrayList<Process>();
        this.readyList = new ArrayList<Process>();
        this.dispatchList = new ArrayList<Process>();
        this.executionList = new ArrayList<Process>();
        this.finishedList = new ArrayList<Process>();
        this.blockList = new ArrayList<Process>();
        this.expirationList = new ArrayList<Process>();
        this.wakeUpList = new ArrayList<Process>();
        this.destroyedList = new ArrayList<Process>();
        this.suspendedList = new ArrayList<Process>();
        this.restartedList = new ArrayList<Process>();
        this.relationList = new ArrayList<>();


    }

    public void addQueueList(Process process) {
        queueList.add(process);
    }

    public void copyToCurrentProcess(){
        for (int i = 0; i < queueList.size(); i++) {
            currentList.add(new Process(queueList.get(i)));
        }
    }
    public void updateProcessInQueue(Process process, int index) {
        queueList.set(index, process);
    }

    public boolean isAlreadyName(String nameProcess) {
        for (Process process : queueList) {
            if (process.getName().equals(nameProcess))
                return true;
        }
        return false;
    }

    public boolean isAlreadyPriority(BigInteger priority){
        for (Process process : queueList) {
            if (process.getPriority().equals(priority))
                return true;
        }
        return false;
    }


    public Object[][] getListAsMatrixObject(ArrayList<Process> list){
        return this.parseArrayListToMatrixObject(list);
    }
    private Object[][] parseArrayListToMatrixObject(ArrayList<Process> list){
        int sizeQueue = list.size();
        Object[][] processList = new Object[sizeQueue][5];

        for(int i = 0; i < sizeQueue; i++){
            processList[i][0] = list.get(i).getName();
            processList[i][1] = list.get(i).getTime();
            processList[i][2] = list.get(i).isLock();
            processList[i][3] = list.get(i).isSuspended();
            processList[i][4] = list.get(i).getPriority();

        }

        return processList;
    }

    public Object[][] getRelationsAsMatrixObject(){
        Object[][] relationMatrix = new Object[this.relationList.size()][2];
        for(int i = 0; i < relationList.size(); i++){
            relationMatrix[i][0] = this.relationList.get(i).get(0).getName();
            relationMatrix[i][1] = this.relationList.get(i).get(1).getName();
        }
        return relationMatrix;
    }

    public void addDestroyedList(Process process){
        destroyedList.add(process);
    }

    public void sendCPU() {
        this.cleanAllLists();
        this.initLoadToReady();
        int i = 0;
        if(this.readyList.size() > 0){
            boolean canContinue = true;
            while (canContinue) {
                this.loadToDispatchQueue(new Process(readyList.get(i)));
                this.loadToExecQueue(new Process(readyList.get(i)));

                if(!(readyList.get(i).getTime().equals("0"))){
                    if ((readyList.get(i).getTime().compareTo(new BigInteger(String.valueOf(PROCESS_TIME))) > 0) && !readyList.get(i).isLock()) {
                        this.loadToExpirationTimeQueue(new Process(readyList.get(i).getName(), this.consumeTimeProcess(readyList.get(i)), readyList.get(i).isLock(), readyList.get(i).getPriority(), readyList.get(i).isSuspended()));
                        this.loadToReadyQueue(new Process(new Process(readyList.get(i).getName(), this.consumeTimeProcess(readyList.get(i)), readyList.get(i).isLock(), readyList.get(i).getPriority(), readyList.get(i).isSuspended())));
                    } else if ((readyList.get(i).getTime().compareTo(new BigInteger(String.valueOf(PROCESS_TIME))) > 0) && readyList.get(i).isLock()) {
                        this.loadToBlockList(new Process(readyList.get(i).getName(), this.consumeTimeProcess(readyList.get(i)), readyList.get(i).isLock(), readyList.get(i).getPriority(), readyList.get(i).isSuspended()));
                        this.loadToWakeUpList(new Process(readyList.get(i).getName(), this.consumeTimeProcess(readyList.get(i)), readyList.get(i).isLock(), readyList.get(i).getPriority(), readyList.get(i).isSuspended()));
                        this.loadToReadyQueue(new Process(readyList.get(i).getName(), this.consumeTimeProcess(readyList.get(i)), readyList.get(i).isLock(), readyList.get(i).getPriority(), readyList.get(i).isSuspended()));
                    }else {
                        this.loadToFinishedQueue(new Process(readyList.get(i).getName(), new BigInteger("0"), readyList.get(i).isLock(), new BigInteger(String.valueOf(readyList.get(i).getPriority())), readyList.get(i).isSuspended()));
                    }
                }
                else
                    this.loadToFinishedQueue(new Process(readyList.get(i)));

                i++;
                if((readyList.size() <= i))
                    canContinue = false;

            }
        }

    }

    public Process searchProcessByName(String nameProcess){
        for(int i = 0; i < queueList.size(); i++){
            if(queueList.get(i).getName().equals(nameProcess)){
                return queueList.get(i);
            }

        }
        return null;
    }

    private void cleanAllLists(){
        this.currentList.clear();
        this.readyList.clear();
        this.dispatchList.clear();
        this.executionList.clear();
        this.finishedList.clear();
        this.blockList.clear();
        this.expirationList.clear();
        this.wakeUpList.clear();
        this.destroyedList.clear();
        this.suspendedList.clear();
        this.restartedList.clear();
    }

    private void loadToDispatchQueue(Process process) {
        this.dispatchList.add(process);
    }

    private void loadToExecQueue(Process process) {
        this.executionList.add(process);
    }

    private BigInteger consumeTimeProcess(Process process) {
        return (process.getTime().subtract( new BigInteger(String.valueOf(PROCESS_TIME))));
    }


    private void loadToReadyQueue(Process process) {
        this.readyList.add(process);
    }

    private void loadToFinishedQueue(Process process) {
        this.finishedList.add(process);
    }

    private void loadToBlockList(Process process) {
        this.blockList.add(process);
    }

    private void loadToExpirationTimeQueue(Process process) {
        this.expirationList.add(process);
    }

    private void loadToWakeUpList(Process process) {
        this.wakeUpList.add(process);
    }

    private void initLoadToReady() {
        readyList.addAll(queueList);
    }

    public Process getProcess(int indexProcess) {
        return queueList.get(indexProcess);
    }

    public void deleteProcess(int indexProcess) {
        this.addDestroyedList(new Process(queueList.get(indexProcess)));
        queueList.remove(indexProcess);
    }

    public ArrayList<Process> getQueueList(){
        return (ArrayList<Process>) this.queueList;
    }
    public ArrayList<Process> getCurrentList(){
            return (ArrayList<Process>) this.currentList;
    }

    public ArrayList<Process> getReadyList(){
        return (ArrayList<Process>) this.readyList;
    }

    public ArrayList<Process> getDispatchList(){
        return (ArrayList<Process>) this.dispatchList;
    }
    public ArrayList<Process> getExecutionList(){
        return (ArrayList<Process>) this.executionList;
    }

    public ArrayList<Process> getExpiredList(){
        return (ArrayList<Process>) this.expirationList;
    }
    public ArrayList<Process> getBlockedList(){
        return (ArrayList<Process>) this.blockList;
    }

    public ArrayList<Process> getWakeUpList(){
        return (ArrayList<Process>) this.wakeUpList;
    }

    public ArrayList<Process> getFinishedList(){
        return (ArrayList<Process>) this.finishedList;
    }

    public ArrayList<Process> getDestroyedList() {
        return (ArrayList<Process>) this.destroyedList;
    }

    public ArrayList<Process> getSuspendedList(){
        return (ArrayList<Process>) this.suspendedList;
    }

    public ArrayList<Process> getRestartedList(){
        return (ArrayList<Process>) this.restartedList;
    }

    public String[] getProcessNameAsArray(){
        String[] queueListAsArray = new String[queueList.size()];
        for(int i = 0; i < queueListAsArray.length; i++){
            queueListAsArray[i] = queueList.get(i).getName();
        }
        return  queueListAsArray;
    }

    public boolean existRelation(Process processOne, Process processTwo){
        for(int i = 0; i < relationList.size(); i++){
            if((relationList.get(i).get(0).equals(processOne) && relationList.get(i).get(1).equals(processTwo))
            || (relationList.get(i).get(0).equals(processTwo) && relationList.get(i).get(1).equals(processOne))){
                return true;
            }
        }
        return false;
    }

    public void addRelation(Process processOne, Process processTwo){
        ArrayList<Process> listProcess = new ArrayList<>();
        listProcess.add(processOne);
        listProcess.add(processTwo);
        this.relationList.add(listProcess);
    }

    public boolean hasRelation(Process process){
        for(int i = 0; i < relationList.size(); i++){
            if(relationList.get(i).get(0).getName().equals(process.getName()) || relationList.get(i).get(1).getName().equals(process.getName())){
                return true;
            }
        }
        return false;
    }

    public void cleanQueueList(){
        queueList.clear();
    }

    public void deleteRelation(int indexRelation) {
        relationList.remove(indexRelation);
    }

    public void updateRelation(String currentName, String modifyNameProcess) {
        for(int i = 0; i < relationList.size(); i++){
            if(relationList.get(i).get(0).getName().equals(currentName))
                relationList.get(i).get(0).setName(modifyNameProcess);
            else if(relationList.get(i).get(1).getName().equals(currentName))
                relationList.get(i).get(1).setName(modifyNameProcess);
        }
    }
}
