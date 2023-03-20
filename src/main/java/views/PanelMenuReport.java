/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author julie
 */
public class PanelMenuReport extends JPanel{
    
    private ButtonMenu existingProcesses, readyReport, dispatchedReport, executionReport, expirationReport, blockReport, wakeReport, finishedReport, destroyedReport, suspendedReport, back;
    private JLabel titleMenuReports;
    
    public PanelMenuReport(ActionListener listener){
        this.setLayout(new GridBagLayout());
        this.setBackground(Color.decode("#4a4e69"));
        this.setPreferredSize(new Dimension(350,80));
        this.setMaximumSize(new Dimension(350,80));
        this.setMinimumSize(new Dimension(350,80));
        initComponents(listener);
        this.setVisible(true);
    }
    
    private void initComponents(ActionListener listener){
        titleMenuReports = new JLabel("Reportes");
        titleMenuReports.setForeground(Color.WHITE);
        titleMenuReports.setFont(ConstantsGUI.FONT_MENU_TITLE);
        addComponent(titleMenuReports, 0, 1);
        
        existingProcesses = new ButtonMenu("Procesos actuales");
        existingProcesses.addActionListener(listener);
        existingProcesses.setActionCommand("Actuales");
        addComponent(existingProcesses, 0, 2);
        
        readyReport = new ButtonMenu("Listos");
        readyReport.addActionListener(listener);
        readyReport.setActionCommand("Listos");
        addComponent(readyReport, 0, 3);
        
        dispatchedReport = new ButtonMenu("Despachados");
        dispatchedReport.addActionListener(listener);
        dispatchedReport.setActionCommand("Despachados");
        addComponent(dispatchedReport, 0, 4);
        
        executionReport = new ButtonMenu("Ejecución");
        executionReport.addActionListener(listener);
        executionReport.setActionCommand("Ejecucion");
        addComponent(executionReport, 0, 5);
        
        expirationReport = new ButtonMenu("Expirados");
        expirationReport.addActionListener(listener);
        expirationReport.setActionCommand("Expirados");
        addComponent(expirationReport, 0, 6);
        
        blockReport = new ButtonMenu("Bloqueados");
        blockReport.addActionListener(listener);
        blockReport.setActionCommand("Bloqueados");
        addComponent(blockReport, 0, 7);
        
        wakeReport = new ButtonMenu("Despertar");
        wakeReport.addActionListener(listener);
        wakeReport.setActionCommand("Despertar");
        addComponent(wakeReport, 0, 8);
        
        finishedReport = new ButtonMenu("Finalizados");
        finishedReport.addActionListener(listener);
        finishedReport.setActionCommand("Finalizados");
        addComponent(finishedReport, 0, 9);

        destroyedReport = new ButtonMenu("Destruidos");
        destroyedReport.addActionListener(listener);
        destroyedReport.setActionCommand("Destruidos");
        addComponent(destroyedReport, 0, 10);

        suspendedReport = new ButtonMenu("Suspendidos");
        suspendedReport.addActionListener(listener);
        suspendedReport.setActionCommand("Suspendidos");
        addComponent(suspendedReport, 0, 11);

        back = new ButtonMenu("Atrás");
        back.addActionListener(listener);
        back.setActionCommand("Atras");
        addComponent(back, 0, 12);
        
    }
    
        public void addComponent(JComponent component, int x, int y){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        this.add(component, gbc);
    }
    
}
