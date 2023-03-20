/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presenters;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import models.ProcessManager;
import models.Process;
import persistence.PersistenceManager;
import views.Utilities;
import views.ViewManager;

/**
 *
 * @author julie
 */
public class Presenter implements ActionListener{
    
    private ViewManager viewManager;
    private ProcessManager processManager;
    
    public Presenter(){
        viewManager = new ViewManager(this);
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
                this.modifyDataProcess();
                break;
            case "Eliminar":
                this.deleteProcess();
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
            case "Atras":
                this.changeToMainMenu();
                break;
            case "Salir":
                System.exit(0);
        }
    }
    
    private void createProcess(){
        viewManager.showCreateProcessPanel();
    }

    private void confirmCreateProcess(){
        String nameProcess = viewManager.getNameProcess();
        double timeProcess = viewManager.getTimeProcess();
        boolean isBlocked = viewManager.getIsBlocked();
        int priority = viewManager.getPriority();
        boolean isSuspended = viewManager.getIsSuspended();


        if(!processManager.isAlreadyName(nameProcess) && !nameProcess.trim().isEmpty()){
            Process newProcess = new Process(nameProcess, timeProcess, isBlocked, priority, isSuspended);
            processManager.addQueueList(newProcess);
            viewManager.hideCreateDialog();
            viewManager.setValuesToTableProcessInQueue(processManager.getListAsMatrixObject(processManager.getQueueList()));
        }
        else if(processManager.isAlreadyName(nameProcess))
            Utilities.showErrorDialog("Nombre ya existente", "Error");

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
        viewManager.showDestroyedProcessReport(processManager.getListAsMatrixObject(processManager.getDestroyedList()));
    }

    private void showSuspendedProcessReport(){
        viewManager.showSuspendedProcessReport(processManager.getListAsMatrixObject(processManager.getSuspendedList()));
    }

    private void showRestartedReportProcessReport(){
        viewManager.showRestartedReportProcessReport(processManager.getListAsMatrixObject(processManager.getRestartedList()));
    }
    private void modifyProcess(){
        if(viewManager.getIndexDataProcess() == -1){
            Utilities.showErrorDialog("Debe seleccionar un proceso", "Error");
        }else{
            Process process = processManager.getProcess(viewManager.getIndexDataProcess());
            viewManager.setInputNameProcess(process.getName());
            viewManager.setInputTimeProcess(process.getTime());
            viewManager.setRadioButton(process.isIsLock());
            viewManager.showModifyProcessPanel();
        }

    }

    private void modifyDataProcess(){
        Process process1 = processManager.getProcess(viewManager.getIndexDataProcess());
        Process process = new Process(viewManager.getModifyNameProcess(),viewManager.getModifyTimeProcess(),viewManager.getModifyIsBlocked(), viewManager.getModifyPriority(), viewManager.getModifyIsSuspended());
        if(process1.getName().equals(viewManager.getModifyNameProcess())){
            processManager.updateProcessInQueue(process,viewManager.getIndexDataProcess());
            viewManager.hideModifyDialog();
            viewManager.setValuesToTableProcessInQueue(processManager.getListAsMatrixObject(processManager.getQueueList()));
        }else if(processManager.isAlreadyName(viewManager.getModifyNameProcess())){
            Utilities.showErrorDialog("Nombre ya existente", "Error");
        }
        else{
            processManager.updateProcessInQueue(process,viewManager.getIndexDataProcess());
            viewManager.hideModifyDialog();
            viewManager.setValuesToTableProcessInQueue(processManager.getListAsMatrixObject(processManager.getQueueList()));
        }
    }

    private void deleteProcess(){
        if(viewManager.getIndexDataProcess() == -1){
            Utilities.showErrorDialog("Debe seleccionar un proceso", "Error");
        }else{
            int confirmation = Utilities.showConfirmationWarning();
            if(confirmation == 0){
                processManager.deleteProcess(viewManager.getIndexDataProcess());
                viewManager.setValuesToTableProcessInQueue(processManager.getListAsMatrixObject(processManager.getQueueList()));
                viewManager.showDestroyedProcessReport(processManager.getListAsMatrixObject(processManager.getDestroyedList()));
            }
        }
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

}
    

