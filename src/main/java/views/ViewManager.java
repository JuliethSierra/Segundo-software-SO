/*

 */
package views;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.math.BigInteger;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author julie
 */
public class ViewManager extends JFrame{

    private final Object[] HEADERS_TABLE = ConstantsGUI.headers;
    private final String[] HEADERS_RELATED_TABLE = ConstantsGUI.HEADERS_RELATED_TABLE;


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
    private JDCreateRelation jdCreateRelation;
    private RelatedProcess relatedProcess;
    
    public ViewManager(ActionListener listener, KeyListener keyListener){
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
        initComponents(listener, keyListener);
        this.setVisible(true);
    }
    
    private void initComponents(ActionListener listener, KeyListener keyListener){
        panelMenu = new PanelMenu(listener);
        this.add(panelMenu, BorderLayout.WEST);

        panelTableProcess = new PanelTableProcess();
        this.add(panelTableProcess, BorderLayout.CENTER);

        panelMenuReport = new PanelMenuReport(listener);
        jpCreateProcess = new JPCreateProcess(listener, keyListener);
        panelReadyReport = new PanelReadyReport();
        jdModifyProcess = new JDModifyProcess(listener, keyListener);
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
        jdCreateRelation = new JDCreateRelation(listener);
        relatedProcess = new RelatedProcess();
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

    public void changeToCreateProcessPanel(){
        this.hideAllPanels();
        this.panelTableProcess.setVisible(true);

    }
    public void showCreateProcessPanel(){
        jpCreateProcess.setVisible(true);
        SwingUtilities.updateComponentTreeUI(this);
    }

    public String getNameProcess(){
        return jpCreateProcess.getNameProcess();
    }

    public BigInteger getTimeProcess(){
        return jpCreateProcess.getTimeProcess();
    }

    public boolean getIsBlocked(){
        return jpCreateProcess.getIsBlocked();
    }

    public BigInteger getPriority(){
        return jpCreateProcess.getPriority();
    }

    public boolean getIsSuspended(){
        return jpCreateProcess.getIsSuspended();
    }


    public void showCurrentProcessReport(Object[][] queueProcess){
        Object[][] newQueueList =  this.parseValuesIsBlockAndIsSuspended(queueProcess);
        DefaultTableModel defaultTableModel = new DefaultTableModel(newQueueList, this.HEADERS_TABLE);
        this.panelCurrentProcess.setTableProcess(defaultTableModel);
        this.hideAllPanels();
        this.panelCurrentProcess.setVisible(true);
        add(panelCurrentProcess, BorderLayout.CENTER);
        SwingUtilities.updateComponentTreeUI(this);
    }

    public void showReadyReport(Object[][] readyProcess){
        Object[][] newList =  this.parseValuesIsBlockAndIsSuspended(readyProcess);
        DefaultTableModel defaultTableModel = new DefaultTableModel(newList, this.HEADERS_TABLE);
        this.panelReadyReport.setTableProcess(defaultTableModel);
        this.hideAllPanels();
        this.panelReadyReport.setVisible(true);
        this.add(this.panelReadyReport, BorderLayout.CENTER);
        SwingUtilities.updateComponentTreeUI(this);
    }

    public void showDispatchedReport(Object[][] dispatchProcess){
        Object[][] newList =  this.parseValuesIsBlockAndIsSuspended(dispatchProcess);
        DefaultTableModel defaultTableModel = new DefaultTableModel(newList, this.HEADERS_TABLE);
        this.panelDispatchedReport.setTableProcess(defaultTableModel);
        this.hideAllPanels();
        this.add(this.panelDispatchedReport, BorderLayout.CENTER);
        this.panelDispatchedReport.setVisible(true);
        SwingUtilities.updateComponentTreeUI(this);
    }

    public void showExecutionReport(Object[][] executionProcess){
        Object[][] newList =  this.parseValuesIsBlockAndIsSuspended(executionProcess);
        DefaultTableModel defaultTableModel = new DefaultTableModel(newList, this.HEADERS_TABLE);
        this.panelExecutionReport.setTableProcess(defaultTableModel);
        this.hideAllPanels();
        this.add(this.panelExecutionReport,BorderLayout.CENTER);
        this.panelExecutionReport.setVisible(true);
        SwingUtilities.updateComponentTreeUI(this);
    }

    public void showExpiredProcessReport(Object[][] expiredProcess){
        Object[][] newList =  this.parseValuesIsBlockAndIsSuspended(expiredProcess);
        DefaultTableModel defaultTableModel = new DefaultTableModel(newList, this.HEADERS_TABLE);
        this.panelExpiredReport.setTableProcess(defaultTableModel);
        this.hideAllPanels();
        this.add(this.panelExpiredReport,BorderLayout.CENTER);
        this.panelExpiredReport.setVisible(true);
        SwingUtilities.updateComponentTreeUI(this);
    }
    public void showBlockedProcessReport(Object[][] blockedList){
        Object[][] newList =  this.parseValuesIsBlockAndIsSuspended(blockedList);
        DefaultTableModel defaultTableModel = new DefaultTableModel(newList, this.HEADERS_TABLE);
        this.panelBlockedReport.setTableProcess(defaultTableModel);
        this.hideAllPanels();
        this.add(this.panelBlockedReport,BorderLayout.CENTER);
        this.panelBlockedReport.setVisible(true);
        SwingUtilities.updateComponentTreeUI(this);
    }
    public void showAwakeProcessReport(Object[][] wakeUpList){
        Object[][] newList =  this.parseValuesIsBlockAndIsSuspended(wakeUpList);
        DefaultTableModel defaultTableModel = new DefaultTableModel(newList, this.HEADERS_TABLE);
        this.panelAwakeReport.setTableProcess(defaultTableModel);
        this.hideAllPanels();
        this.add(this.panelAwakeReport,BorderLayout.CENTER);
        this.panelAwakeReport.setVisible(true);
        SwingUtilities.updateComponentTreeUI(this);
    }

    public void showFinishedProcessReport(Object[][] finishedList){
        Object[][] newList =  this.parseValuesIsBlockAndIsSuspended(finishedList);
        DefaultTableModel defaultTableModel = new DefaultTableModel(newList, this.HEADERS_TABLE);
        this.panelCompletedReport.setTableProcess(defaultTableModel);
        this.hideAllPanels();
        this.add(this.panelCompletedReport,BorderLayout.CENTER);
        this.panelCompletedReport.setVisible(true);
        SwingUtilities.updateComponentTreeUI(this);
    }

    public void showDestroyedProcessReport(Object[][] destroyedList){
        Object[][] newList =  this.parseValuesIsBlockAndIsSuspended(destroyedList);
        DefaultTableModel defaultTableModel = new DefaultTableModel(newList, this.HEADERS_TABLE);
        this.panelDestroyedReport.setTableProcess(defaultTableModel);
        this.hideAllPanels();
        this.add(this.panelDestroyedReport,BorderLayout.CENTER);
        this.panelDestroyedReport.setVisible(true);
        SwingUtilities.updateComponentTreeUI(this);
    }

    public void showSuspendedProcessReport(Object[][] suspendedList){
        Object[][] newList =  this.parseValuesIsBlockAndIsSuspended(suspendedList);
        DefaultTableModel defaultTableModel = new DefaultTableModel(newList, this.HEADERS_TABLE);
        this.panelSuspendedReport.setTableProcess(defaultTableModel);
        this.hideAllPanels();
        this.add(this.panelSuspendedReport,BorderLayout.CENTER);
        this.panelSuspendedReport.setVisible(true);
        SwingUtilities.updateComponentTreeUI(this);
    }

    public void showRestartedReportProcessReport(Object[][] restartedList){
        Object[][] newList =  this.parseValuesIsBlockAndIsSuspended(restartedList);
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
        this.jdCreateRelation.setVisible(false);
        this.relatedProcess.setVisible(false);
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
        Object[][] newQueueList =  this.parseValuesIsBlockAndIsSuspended(queueListAsStringList);
        DefaultTableModel defaultTableModel = new DefaultTableModel(newQueueList, HEADERS_TABLE);
        this.panelTableProcess.setTableProcess(defaultTableModel);
    }

    private Object[][] parseValuesIsBlockAndIsSuspended(Object[][] queueList){
        int size = queueList.length;
        for(int i = 0; i < size; i++){
            queueList[i][2] = queueList[i][2].equals(true) ? "Sí" : "No";
            queueList[i][3] = queueList[i][3].equals(true) ? "Sí" : "No";

        }
        return queueList;
    }

    public int getIndexDataProcess(){
        return panelTableProcess.getIndexDataProcess();
    }

    public void setInputNameProcess(String inputNameProcess) {
        jdModifyProcess.setInputNameProcess(inputNameProcess);
    }

    public void setInputTimeProcess(BigInteger inputTimeProcess) {
        jdModifyProcess.setInputTimeProcess(inputTimeProcess);
    }

    public void setPriority(BigInteger priority){
        this.jdModifyProcess.setPriority(priority);
    }

    public void setIsBlock(boolean isBlock){
        jdModifyProcess.setIsBlock(isBlock);
    }

    public void setIsSuspended(boolean isSuspended){
        this.jdModifyProcess.setIsSuspended(isSuspended);
    }

    public void setRadioButtonSuspended(boolean isSuspended){
        jdModifyProcess.setRadioButtonSuspended(isSuspended);
    }

    public String getModifyNameProcess(){
        return jdModifyProcess.getNameProcess();
    }

    public BigInteger getModifyTimeProcess(){
        return new BigInteger(jdModifyProcess.getTimeProcess().toString());
    }

    public boolean getModifyIsBlocked(){
        return jdModifyProcess.getIsBlocked();
    }

    public String getModifyPriority(){
        return String.valueOf(jdModifyProcess.getModifyPriority());
    }

    public boolean getModifyIsSuspended(){
        return jdModifyProcess.getModifyIsSuspended();
    }

    public void setValuesToCombos(String[] processName){
        this.jdCreateRelation.setValuesCombos(processName);
    }
    public void showCreateRelation() {
        this.jdCreateRelation.setVisible(true);
        SwingUtilities.updateComponentTreeUI(this);

    }

    public void hideCreateRelation(){
        this.jdCreateRelation.setVisible(false);
        SwingUtilities.updateComponentTreeUI(this);
    }

    public String getSelectedElementFirstCombo() {
        return this.jdCreateRelation.getSelectedElementFirstCombo();
    }
    public String getSelectedElementSecondCombo() {
        return this.jdCreateRelation.getSelectedElementSecondCombo();
    }

    public void showRelations(){
        this.relatedProcess.setVisible(true);
        SwingUtilities.updateComponentTreeUI(this);
    }

    public void setRelations(Object[][] relationsAsMatrixObject) {
        DefaultTableModel defaultTableModel = new DefaultTableModel(relationsAsMatrixObject, this.HEADERS_RELATED_TABLE);
        this.relatedProcess.setTableProcess(defaultTableModel);
        this.hideAllPanels();
        this.relatedProcess.setVisible(true);
        add(relatedProcess, BorderLayout.CENTER);
        SwingUtilities.updateComponentTreeUI(this);

    }

    public int getIndexRelation() {
        return this.relatedProcess.getIndexRelation();
    }

    public void setValuesToRelations(Object[][] relationsAsMatrixObject) {
        DefaultTableModel defaultTableModel = new DefaultTableModel(relationsAsMatrixObject, HEADERS_RELATED_TABLE);
        this.relatedProcess.setTableProcess(defaultTableModel);
    }
}
