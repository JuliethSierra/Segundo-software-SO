/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presenters;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.math.BigInteger;

import models.ProcessManager;
import models.Process;
import persistence.PersistenceManager;
import views.Utilities;
import views.ViewManager;

/**
 *
 * @author julie
 */
public class Presenter implements ActionListener, KeyListener {
    
    private ViewManager viewManager;
    private ProcessManager processManager;
    private boolean isInCreateOption = true;
    
    public Presenter(){
        viewManager = new ViewManager(this, this);
        processManager = new ProcessManager();
    }
    
    public static void main(String[] args) {
        new Presenter();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case "Agregar":
                this.createProcess();
                break;
            case "Añadir":
                this.confirmCreateProcess();
                break;
            case "Cancelar":
                this.cancelCreateProcess();
                break;
            case "CancelarMod":
                this.cancelModifyProcess();
                break;
            case "Modificar":
                this.modifyProcess();
                break;
            case "Modificado":
                this.modify();
                break;
            case "Eliminar":
                this.deleteProcessAndRelations();
                break;
            case "Relacion":
                this.createRelation();
                break;
            case "Añadir Relacion":
                this.confirmCreateRelation();
                break;
            case "Ver Relaciones":
                this.showRelations();
                break;
            case "Cancelar Relacion":
                this.cancelRelation();
                break;
            case "Reportes":
                this.changeToMenuReports();
                break;
            case "Enviar":
                this.sendCPU();
                break;
            case "Listos":
               this.showReadyReport();
                break;
            case "Actuales":
               this.showCurrentProcessReport();
                break;
            case "Despachados":
                this.showDispatchedReport();
                break;
            case "Ejecucion":
                this.showExecutionReport();
                break;
            case "Expirados":
                this.showExpiredProcessReport();
                break;
            case "Bloqueados":
                this.showBlockedProcessReport();
                break;
            case "Despertar":
                this.showAwakeProcessReport();
                break;
            case "Finalizados":
                this.showFinishedProcessReport();
                break;
            case "Destruidos":
                this.showDestroyedProcessReport();
                break;
            case "Suspendidos":
                this.showSuspendedProcessReport();
                break;
            case "Reanudados":
                this.showRestartedReportProcessReport();
                break;
            case "RelacionadosReport":
                this.showRelatedProcessReport();
                break;
            case "ManualUsuario":
                this.showUserManual();
                break;
            case "Atras":
                this.changeToMainMenu();
                break;
            case "Salir":
                System.exit(0);
                break;
        }
    }
    
    private void createProcess(){
        if(isInCreateOption)
            viewManager.showCreateProcessPanel();
        else {
            viewManager.setValuesToTableProcessInQueue(processManager.getListAsMatrixObject(processManager.getQueueList()));
            viewManager.showTableProcessPanel();
            this.isInCreateOption = true;
        }

    }

    private void confirmCreateProcess(){
        String nameProcess = viewManager.getNameProcess();
        BigInteger timeProcess = viewManager.getTimeProcess();
        boolean isBlocked = viewManager.getIsBlocked();
        BigInteger priority = viewManager.getPriority();
        boolean isSuspended = viewManager.getIsSuspended();


        if(!processManager.isAlreadyName(nameProcess) && !nameProcess.trim().isEmpty() && !processManager.isAlreadyPriority(priority)){
            Process newProcess = new Process(nameProcess, timeProcess, isBlocked, priority, isSuspended);
            processManager.addQueueList(newProcess);
            viewManager.hideCreateDialog();

            viewManager.setValuesToTableProcessInQueue(processManager.getListAsMatrixObject(processManager.getQueueList()));
        }
        else if(processManager.isAlreadyName(nameProcess))
            Utilities.showErrorDialog("Nombre ya existente", "Error");
        else if(processManager.isAlreadyPriority(priority))
            Utilities.showErrorDialog("Prioridad ya existente", "Error");

        else Utilities.showErrorDialog("Nombre inválido", "Error");


    }

    private void sendCPU(){
        int response = Utilities.showConfirmationWarning();
        if(response == 0){
            processManager.sendCPU();
            Utilities.showDoneCPUProcess();
            this.saveReports();
            processManager.copyToCurrentProcess();
            processManager.cleanQueueList();
            this.cleanMainTableProcess();
            viewManager.showTableProcessPanel();
        }
    }

    private void cleanMainTableProcess(){
        this.viewManager.setValuesToTableProcessInQueue(processManager.getListAsMatrixObject(processManager.getQueueList()));
    }


    private void loadProcessInQueue(){
        viewManager.setValuesToTableProcessInQueue(processManager.getListAsMatrixObject(processManager.getQueueList()));
    }
    
    private void cancelCreateProcess(){
        viewManager.hideCreateDialog();
    }

    private void cancelModifyProcess(){
        viewManager.hideModifyDialog();
    }
    
    private void changeToMenuReports(){
        viewManager.changeToReportMenu();
    }


    
    private void changeToMainMenu(){
        viewManager.changeToMainMenu();
        viewManager.setValuesToTableProcessInQueue(processManager.getListAsMatrixObject(processManager.getQueueList()));
        viewManager.showTableProcessPanel();
        processManager.cleanReportsList();
    }
    
    private void showReadyReport(){
        viewManager.showReadyReport(processManager.getListAsMatrixObject(processManager.getReadyList()));
    }
    
    private void showCurrentProcessReport(){
        viewManager.showCurrentProcessReport(processManager.getListAsMatrixObject(processManager.getCurrentList()));
    }

    private void showDispatchedReport(){
        viewManager.showDispatchedReport(processManager.getListAsMatrixObject(processManager.getDispatchList()));
    }

    private void showExecutionReport(){
        viewManager.showExecutionReport(processManager.getListAsMatrixObject(processManager.getExecutionList()));
    }

    private void showExpiredProcessReport(){
        viewManager.showExpiredProcessReport(processManager.getListAsMatrixObject(processManager.getExpiredList()));
    }
    private void showBlockedProcessReport(){
        viewManager.showBlockedProcessReport(processManager.getListAsMatrixObject(processManager.getBlockedList()));
    }
    private void showAwakeProcessReport(){
        viewManager.showAwakeProcessReport(processManager.getListAsMatrixObject(processManager.getWakeUpList()));
    }

    private void showFinishedProcessReport(){
        viewManager.showFinishedProcessReport(processManager.getListAsMatrixObject(processManager.getFinishedList()));
    }

    private void showDestroyedProcessReport(){
        System.out.println(processManager.getDestroyedList());
        viewManager.showDestroyedProcessReport(processManager.getListAsMatrixObject(processManager.getDestroyedList()));
    }

    private void showSuspendedProcessReport(){
        viewManager.showSuspendedProcessReport(processManager.getListAsMatrixObject(processManager.getSuspendedList()));
    }

    private void showRestartedReportProcessReport(){
        viewManager.showRestartedReportProcessReport(processManager.getListAsMatrixObject(processManager.getRestartedList()));
    }

    private void showRelatedProcessReport(){
        viewManager.setRelationsReport((processManager.getRelationsAsMatrixObject()));
        viewManager.showRelationsReport();
    }

    private void showUserManual(){
        PersistenceManager.showManual();
    }
    private void modifyProcess(){
        if(viewManager.getIndexDataProcess() == -1){
            Utilities.showErrorDialog("Debe seleccionar un proceso", "Error");
        }else{
            Process process = processManager.getProcess(viewManager.getIndexDataProcess());
            viewManager.setInputNameProcess(process.getName());
            viewManager.setInputTimeProcess(process.getTime());
            viewManager.setIsBlock(process.isLock());
            viewManager.setIsSuspended(process.isSuspended());
            viewManager.setPriority(process.getPriority());
            viewManager.showModifyProcessPanel();
        }

    }





    public void modify(){
        Process currentProcess = processManager.getProcess(viewManager.getIndexDataProcess());
        Process process = new Process(viewManager.getModifyNameProcess(), viewManager.getModifyTimeProcess(),viewManager.getModifyIsBlocked(), new BigInteger(viewManager.getModifyPriority()), viewManager.getModifyIsSuspended());
        String modifyNameProcess = viewManager.getModifyNameProcess();
        String modifyPriority = viewManager.getModifyPriority();

        if(!currentProcess.getName().equals(modifyNameProcess) && processManager.isAlreadyName(modifyNameProcess)){
            Utilities.showErrorDialog("Ya existe  un proceso con este nombre", "Error");
        }
        else if(!(currentProcess.getPriority().compareTo(new BigInteger(modifyPriority)) == 0) && processManager.isAlreadyPriority(new BigInteger(modifyPriority))){
            Utilities.showErrorDialog("Ya existe  un proceso con esta prioridad", "Error");

        }
        else {
            processManager.updateProcessInQueue(process,viewManager.getIndexDataProcess());
            viewManager.hideModifyDialog();
            viewManager.setValuesToTableProcessInQueue(processManager.getListAsMatrixObject(processManager.getQueueList()));
            processManager.updateRelation(currentProcess.getName(),modifyNameProcess);
        }
    }

    private void deleteProcessAndRelations(){
        if(isInCreateOption){
            if(viewManager.getIndexDataProcess() == -1){
                Utilities.showErrorDialog("Debe seleccionar un proceso", "Error");
            } else if(processManager.hasRelation(processManager.getProcess(viewManager.getIndexDataProcess()))){
                Utilities.showErrorDialog("El proceso tiene relaciones. Elimínelas primero", "Error");
            }
            else{
                int confirmation = Utilities.showConfirmationWarning();
                if(confirmation == 0){
                    processManager.deleteProcess(viewManager.getIndexDataProcess());
                    viewManager.setValuesToTableProcessInQueue(processManager.getListAsMatrixObject(processManager.getQueueList()));

                }
            }
        }
        else {
            if(viewManager.getIndexRelation() == -1){
                Utilities.showErrorDialog("Debe seleccionar una relación", "Error");
            }
            else {
                int confirmation = Utilities.showConfirmationWarning();
                if(confirmation == 0){
                    processManager.deleteRelation(viewManager.getIndexRelation());
                    viewManager.setValuesToRelations(processManager.getRelationsAsMatrixObject());

                }

            }
        }

    }

    private void createRelation(){
        if(processManager.getQueueList().size() > 1){
            this.viewManager.setValuesToCombos(processManager.getProcessNameAsArray());
            this.viewManager.showCreateRelation();
        }
        else {
            Utilities.showErrorDialog("Debe crear al menos dos procesos", "Error");
        }

    }

    private void confirmCreateRelation(){
        Process firstElement = this.processManager.searchProcessByName(this.viewManager.getSelectedElementFirstCombo());
        Process secondElement = this.processManager.searchProcessByName(this.viewManager.getSelectedElementSecondCombo());

        if(firstElement != null && secondElement != null){
            if(!firstElement.equals(secondElement)){
                if(!processManager.existRelation(firstElement, secondElement)){
                    processManager.addRelation(this.processManager.searchProcessByName(firstElement.getName()), this.processManager.searchProcessByName(secondElement.getName()));
                    Utilities.showDoneCreationRelation();
                    viewManager.hideCreateRelation();
                }
                else {
                    Utilities.showErrorDialog("Estos procesos ya se encuentran relacionados", "Error");
                }
            }
            else {
                Utilities.showErrorDialog("No puede relacionar un proceso con él mismo", "Error");
            }
        }

    }

    private void showRelations(){
        this.isInCreateOption = false;
        this.viewManager.setRelations(processManager.getRelationsAsMatrixObject());
        this.viewManager.showRelations();

    }

    private void cancelRelation(){
        this.viewManager.hideCreateRelation();
    }

    private void saveReports(){
        PersistenceManager.saveReport("Entrada.txt", processManager.getQueueList());
        PersistenceManager.saveReport("Listos.txt", processManager.getReadyList());
        PersistenceManager.saveReport("Despachados.txt", processManager.getDispatchList());
        PersistenceManager.saveReport("Ejecución.txt", processManager.getExecutionList());
        PersistenceManager.saveReport("Expirados.txt", processManager.getExpiredList());
        PersistenceManager.saveReport("Bloqueados.txt", processManager.getBlockedList());
        PersistenceManager.saveReport("Despiertos.txt", processManager.getWakeUpList());
        PersistenceManager.saveReport("Finalizados.txt", processManager.getFinishedList());
    }

    @Override
    public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();
        if (!Character.isDigit(c)) {
            e.consume(); // Evita que se ingrese el carácter no válido
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
    

