/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package views;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author julie
 */
public class ViewManager extends JFrame{

    private final Object[] HEADERS_TABLE = ConstantsGUI.headers;


    private PanelMenu panelMenu;
    private PanelMenuReport panelMenuReport;
    private PanelTableProcess panelTableProcess;
    private JPCreateProcess jpCreateProcess;
    private PanelReadyReport panelReadyReport;
    private JDModifyProcess jdModifyProcess;
    private PanelDispatchedReport panelDispatchedReport;
    private PanelExecutionReport panelExecutionReport;
    private PanelExpiredReport panelExpiredReport;
    private PanelBlockedReport panelBlockedReport;
    private PanelAwakeReport panelAwakeReport;
    private PanelCompletedReport panelCompletedReport;
    private PanelCurrentProcess panelCurrentProcess;

    private PanelDestroyedReport panelDestroyedReport;

    private PanelSuspendedReport panelSuspendedReport;

    private PanelRestartedReport panelRestartedReport;
    
    public ViewManager(ActionListener listener){
        this.setLayout(new BorderLayout());
        this.setTitle("Procesos");
        this.setFont(ConstantsGUI.MAIN_MENU);
        this.setSize(700, 500);
        this.setUndecorated(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setExtendedState(MAXIMIZED_BOTH);
        this.getContentPane().setBackground(Color.decode("#f2e9e4"));
        initComponents(listener);
        this.setVisible(true);
    }
    
    private void initComponents(ActionListener listener){
        panelMenu = new PanelMenu(listener);
        this.add(panelMenu, BorderLayout.WEST);

        panelTableProcess = new PanelTableProcess();
        this.add(panelTableProcess, BorderLayout.CENTER);

        panelMenuReport = new PanelMenuReport(listener);
        jpCreateProcess = new JPCreateProcess(listener);


        panelReadyReport = new PanelReadyReport();
        jdModifyProcess = new JDModifyProcess(listener);
        panelDispatchedReport = new PanelDispatchedReport();
        panelExecutionReport = new PanelExecutionReport();
        panelExpiredReport = new PanelExpiredReport();
        panelBlockedReport = new PanelBlockedReport();
        panelAwakeReport = new PanelAwakeReport();
        panelCompletedReport = new PanelCompletedReport();
        panelCurrentProcess = new PanelCurrentProcess();
        panelDestroyedReport = new PanelDestroyedReport();
        panelSuspendedReport = new PanelSuspendedReport();
        panelRestartedReport = new PanelRestartedReport();
    }


    public void showTableProcessPanel(){
        this.hideAllPanels();
        panelTableProcess.setVisible(true);
        this.add(panelTableProcess, BorderLayout.CENTER);
        SwingUtilities.updateComponentTreeUI(this);
    }
    public void changeToReportMenu(){
        this.remove(panelMenu);
        this.add(panelMenuReport, BorderLayout.WEST);
        SwingUtilities.updateComponentTreeUI(this);
    }
    
    public void changeToMainMenu(){
        this.remove(panelMenuReport);
        this.add(panelMenu, BorderLayout.WEST);
        SwingUtilities.updateComponentTreeUI(this);
    }
    
    public void showCreateProcessPanel(){
        jpCreateProcess.setVisible(true);
        SwingUtilities.updateComponentTreeUI(this);
    }

    public String getNameProcess(){
        return jpCreateProcess.getNameProcess();
    }

    public double getTimeProcess(){
        double timeProcess = 0;
        timeProcess = Double.parseDouble(jpCreateProcess.getTimeProcess().toString());
        return timeProcess;
    }

    public boolean getIsBlocked(){
        return jpCreateProcess.getIsBlocked();
    }

    public int getPriority(){
        return jpCreateProcess.getPriority();
    }

    public boolean getIsSuspended(){
        return jpCreateProcess.getIsSuspended();
    }


    public void showCurrentProcessReport(Object[][] queueProcess){
        Object[][] newQueueList =  this.parseValuesIsBlock(queueProcess);
        DefaultTableModel defaultTableModel = new DefaultTableModel(newQueueList, this.HEADERS_TABLE);
        this.panelCurrentProcess.setTableProcess(defaultTableModel);
        this.hideAllPanels();
        this.panelCurrentProcess.setVisible(true);
        add(panelCurrentProcess, BorderLayout.CENTER);
        SwingUtilities.updateComponentTreeUI(this);
    }

    public void showReadyReport(Object[][] readyProcess){
        Object[][] newList =  this.parseValuesIsBlock(readyProcess);
        DefaultTableModel defaultTableModel = new DefaultTableModel(newList, this.HEADERS_TABLE);
        this.panelReadyReport.setTableProcess(defaultTableModel);
        this.hideAllPanels();
        this.panelReadyReport.setVisible(true);
        this.add(this.panelReadyReport, BorderLayout.CENTER);
        SwingUtilities.updateComponentTreeUI(this);
    }

    public void showDispatchedReport(Object[][] dispatchProcess){
        Object[][] newList =  this.parseValuesIsBlock(dispatchProcess);
        DefaultTableModel defaultTableModel = new DefaultTableModel(newList, this.HEADERS_TABLE);
        this.panelDispatchedReport.setTableProcess(defaultTableModel);
        this.hideAllPanels();
        this.add(this.panelDispatchedReport, BorderLayout.CENTER);
        this.panelDispatchedReport.setVisible(true);
        SwingUtilities.updateComponentTreeUI(this);
    }

    public void showExecutionReport(Object[][] executionProcess){
        Object[][] newList =  this.parseValuesIsBlock(executionProcess);
        DefaultTableModel defaultTableModel = new DefaultTableModel(newList, this.HEADERS_TABLE);
        this.panelExecutionReport.setTableProcess(defaultTableModel);
        this.hideAllPanels();
        this.add(this.panelExecutionReport,BorderLayout.CENTER);
        this.panelExecutionReport.setVisible(true);
        SwingUtilities.updateComponentTreeUI(this);
    }

    public void showExpiredProcessReport(Object[][] expiredProcess){
        Object[][] newList =  this.parseValuesIsBlock(expiredProcess);
        DefaultTableModel defaultTableModel = new DefaultTableModel(newList, this.HEADERS_TABLE);
        this.panelExpiredReport.setTableProcess(defaultTableModel);
        this.hideAllPanels();
        this.add(this.panelExpiredReport,BorderLayout.CENTER);
        this.panelExpiredReport.setVisible(true);
        SwingUtilities.updateComponentTreeUI(this);
    }
    public void showBlockedProcessReport(Object[][] blockedList){
        Object[][] newList =  this.parseValuesIsBlock(blockedList);
        DefaultTableModel defaultTableModel = new DefaultTableModel(newList, this.HEADERS_TABLE);
        this.panelBlockedReport.setTableProcess(defaultTableModel);
        this.hideAllPanels();
        this.add(this.panelBlockedReport,BorderLayout.CENTER);
        this.panelBlockedReport.setVisible(true);
        SwingUtilities.updateComponentTreeUI(this);
    }
    public void showAwakeProcessReport(Object[][] wakeUpList){
        Object[][] newList =  this.parseValuesIsBlock(wakeUpList);
        DefaultTableModel defaultTableModel = new DefaultTableModel(newList, this.HEADERS_TABLE);
        this.panelAwakeReport.setTableProcess(defaultTableModel);
        this.hideAllPanels();
        this.add(this.panelAwakeReport,BorderLayout.CENTER);
        this.panelAwakeReport.setVisible(true);
        SwingUtilities.updateComponentTreeUI(this);
    }

    public void showFinishedProcessReport(Object[][] finishedList){
        Object[][] newList =  this.parseValuesIsBlock(finishedList);
        DefaultTableModel defaultTableModel = new DefaultTableModel(newList, this.HEADERS_TABLE);
        this.panelCompletedReport.setTableProcess(defaultTableModel);
        this.hideAllPanels();
        this.add(this.panelCompletedReport,BorderLayout.CENTER);
        this.panelCompletedReport.setVisible(true);
        SwingUtilities.updateComponentTreeUI(this);
    }

    public void showDestroyedProcessReport(Object[][] destroyedList){
        Object[][] newList =  this.parseValuesIsBlock(destroyedList);
        DefaultTableModel defaultTableModel = new DefaultTableModel(newList, this.HEADERS_TABLE);
        this.panelDestroyedReport.setTableProcess(defaultTableModel);
        this.hideAllPanels();
        this.add(this.panelDestroyedReport,BorderLayout.CENTER);
        this.panelDestroyedReport.setVisible(true);
        SwingUtilities.updateComponentTreeUI(this);
    }

    public void showSuspendedProcessReport(Object[][] suspendedList){
        Object[][] newList =  this.parseValuesIsBlock(suspendedList);
        DefaultTableModel defaultTableModel = new DefaultTableModel(newList, this.HEADERS_TABLE);
        this.panelSuspendedReport.setTableProcess(defaultTableModel);
        this.hideAllPanels();
        this.add(this.panelSuspendedReport,BorderLayout.CENTER);
        this.panelSuspendedReport.setVisible(true);
        SwingUtilities.updateComponentTreeUI(this);
    }

    public void showRestartedReportProcessReport(Object[][] restartedList){
        Object[][] newList =  this.parseValuesIsBlock(restartedList);
        DefaultTableModel defaultTableModel = new DefaultTableModel(newList, this.HEADERS_TABLE);
        this.panelRestartedReport.setTableProcess(defaultTableModel);
        this.hideAllPanels();
        this.add(this.panelRestartedReport,BorderLayout.CENTER);
        this.panelRestartedReport.setVisible(true);
        SwingUtilities.updateComponentTreeUI(this);
    }

    private void hideAllPanels(){
        this.panelTableProcess.setVisible(false);
        this.panelReadyReport.setVisible(false);
        this.panelDispatchedReport.setVisible(false);
        this.panelExecutionReport.setVisible(false);
        this.panelBlockedReport.setVisible(false);
        this.panelExpiredReport.setVisible(false);
        this.panelAwakeReport.setVisible(false);
        this.panelCompletedReport.setVisible(false);
        this.panelCurrentProcess.setVisible(false);
        this.panelDestroyedReport.setVisible(false);
        this.panelSuspendedReport.setVisible(false);
        this.panelRestartedReport.setVisible(false);
        SwingUtilities.updateComponentTreeUI(this);
    }
    public void showModifyProcessPanel(){
        jdModifyProcess.setVisible(true);
        SwingUtilities.updateComponentTreeUI(this);
    }

    public void hideCreateDialog(){
        this.jpCreateProcess.cleanFields();
        this.jpCreateProcess.setVisible(false);
        SwingUtilities.updateComponentTreeUI(this);
    }

    public void hideModifyDialog(){
        this.jdModifyProcess.cleanFields();
        this.jdModifyProcess.setVisible(false);
        SwingUtilities.updateComponentTreeUI(this);
    }

    public void setValuesToTableProcessInQueue(Object[][] queueListAsStringList){
        Object[][] newQueueList =  this.parseValuesIsBlock(queueListAsStringList);
        DefaultTableModel defaultTableModel = new DefaultTableModel(newQueueList, HEADERS_TABLE);
        this.panelTableProcess.setTableProcess(defaultTableModel);
    }

    private Object[][] parseValuesIsBlock(Object[][] queueList){
        int size = queueList.length;
        for(int i = 0; i < size; i++){
            queueList[i][2] = queueList[i][2].equals(true) ? "Sí" : "No";
        }
        return queueList;
    }

    public int getIndexDataProcess(){
        return panelTableProcess.getIndexDataProcess();
    }

    public void setInputNameProcess(String inputNameProcess) {
        jdModifyProcess.setInputNameProcess(inputNameProcess);
    }

    public void setInputTimeProcess(double inputTimeProcess) {
        jdModifyProcess.setInputTimeProcess(inputTimeProcess);
    }

    public void setRadioButton(boolean isblock){
        jdModifyProcess.setRadioButton(isblock);
    }

    public void setRadioButtonSuspended(boolean isSuspended){
        jdModifyProcess.setRadioButtonSuspended(isSuspended);
    }

    public String getModifyNameProcess(){
        return jdModifyProcess.getNameProcess();
    }

    public double getModifyTimeProcess(){
        return Double.parseDouble(jdModifyProcess.getTimeProcess().toString());
    }

    public boolean getModifyIsBlocked(){
        return jdModifyProcess.getIsBlocked();
    }

    public int getModifyPriority(){
        return jdModifyProcess.getModifyPriority();
    }

    public boolean getModifyIsSuspended(){
        return jdModifyProcess.getModifyIsSuspended();
    }

}
