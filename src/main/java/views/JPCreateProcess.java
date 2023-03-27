/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.math.BigInteger;
import java.security.Key;
import javax.swing.*;
import javax.swing.text.DefaultFormatter;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import static presenters.Presenter.main;

/**
 *
 * @author julie
 */
public class JPCreateProcess extends JDialog {
    private ButtonActions create, cancel;
    private JTextField inputNameProcess, inputPriority, inputTimeProcess;
    private JLabel nameProcess, timeProcess, blockProcess, priorityProcess, suspendedProcess;
    private JRadioButton yesButton, noButton, yesButtonSuspended, noButtonSuspended;
    private ButtonGroup groupRadioButton, groupRadioButtonSuspended;
    private JPanel panelGroup, panelGroupSuspended;
    
    public JPCreateProcess(ActionListener listener, KeyListener keyListener){
        this.setModal(true);
        this.setTitle("Crear Proceso");
        this.setLayout(new GridBagLayout());
        this.setFont(ConstantsGUI.MAIN_MENU);
        this.setSize(400, 280);
        this.setUndecorated(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.getContentPane().setBackground(Color.decode("#C9ADA7"));
        initComponents(listener, keyListener);
    }
    
    private void initComponents(ActionListener listener, KeyListener keyListener){
        nameProcess = new JLabel("Nombre");
        nameProcess.setFont(ConstantsGUI.FONT_TITLE_INPUTS);
        addComponent(nameProcess, 0, 0);
        
        inputNameProcess = new JTextField(10);
        inputNameProcess.setSize(100,50);
        inputNameProcess.setPreferredSize(new Dimension(100,30));
        inputNameProcess.setBackground(Color.WHITE);
        inputNameProcess.setFont(ConstantsGUI.FONT_INPUTS);
        addComponent(inputNameProcess, 1, 0);
        
        timeProcess = new JLabel("Tiempo");
        timeProcess.setFont(ConstantsGUI.FONT_TITLE_INPUTS);
        addComponent(timeProcess, 0, 1);

        inputTimeProcess = new JTextField(10);
        inputTimeProcess.addKeyListener(keyListener);
        inputTimeProcess.setSize(100,50);
        inputTimeProcess.setPreferredSize(new Dimension(100,30));
        inputTimeProcess.setBackground(Color.WHITE);
        inputTimeProcess.setFont(ConstantsGUI.FONT_INPUTS);
        addComponent(inputTimeProcess, 1, 1);

        priorityProcess = new JLabel("Prioridad");
        priorityProcess.setFont(ConstantsGUI.FONT_TITLE_INPUTS);
        addComponent(priorityProcess,0 ,2 );

        inputPriority = new JTextField(10);
        inputPriority.setSize(100,50);
        inputPriority.setPreferredSize(new Dimension(100,30));
        inputPriority.setBackground(Color.WHITE);
        inputPriority.setFont(ConstantsGUI.FONT_INPUTS);
        inputPriority.addKeyListener(keyListener);
        addComponent(inputPriority, 1, 2);

        blockProcess = new JLabel("Bloquear");
        blockProcess.setFont(ConstantsGUI.FONT_TITLE_INPUTS);
        addComponent(blockProcess, 0, 3);
        
        groupRadioButton = new ButtonGroup();
        
        yesButton = new JRadioButton("Si");
        yesButton.setBackground(Color.decode("#C9ADA7"));
        yesButton.setForeground(Color.BLACK);
        yesButton.setFont(ConstantsGUI.FONT_MENU_ACTIONS);
        
        noButton = new JRadioButton("No");
        noButton.setBackground(Color.decode("#C9ADA7"));
        noButton.setForeground(Color.BLACK);      
        noButton.setFont(ConstantsGUI.FONT_MENU_ACTIONS);
        
        groupRadioButton.add(yesButton);
        groupRadioButton.add(noButton);
        yesButton.setSelected(true);
        panelGroup = new JPanel();
        panelGroup.setBackground(Color.decode("#C9ADA7"));
        panelGroup.add(yesButton);
        panelGroup.add(noButton);
        addComponent(panelGroup, 1, 3);

        suspendedProcess = new JLabel("Suspender");
        suspendedProcess.setFont(ConstantsGUI.FONT_TITLE_INPUTS);
        addComponent(suspendedProcess,0 , 4);

        groupRadioButtonSuspended = new ButtonGroup();

        yesButtonSuspended = new JRadioButton("Si");
        yesButtonSuspended.setBackground(Color.decode("#C9ADA7"));
        yesButtonSuspended.setForeground(Color.BLACK);
        yesButtonSuspended.setFont(ConstantsGUI.FONT_MENU_ACTIONS);

        noButtonSuspended = new JRadioButton("No");
        noButtonSuspended.setBackground(Color.decode("#C9ADA7"));
        noButtonSuspended.setForeground(Color.BLACK);
        noButtonSuspended.setFont(ConstantsGUI.FONT_MENU_ACTIONS);

        groupRadioButtonSuspended.add(yesButtonSuspended);
        groupRadioButtonSuspended.add(noButtonSuspended);
        yesButtonSuspended.setSelected(true);
        panelGroupSuspended = new JPanel();
        panelGroupSuspended.setBackground(Color.decode("#C9ADA7"));
        panelGroupSuspended.add(yesButtonSuspended);
        panelGroupSuspended.add(noButtonSuspended);
        addComponent(panelGroupSuspended, 1, 4);

        create = new ButtonActions("Añadir");
        create.addActionListener(listener);
        create.setActionCommand("Añadir");
        create.setPreferredSize(new Dimension(150, 35));
        addComponent(create,0,5);

        cancel = new ButtonActions("Cancelar");
        cancel.addActionListener(listener);
        cancel.setActionCommand("Cancelar");
        cancel.setPreferredSize(new Dimension(150, 35));
        addComponent(cancel,1,5);
        
    }

        public void addComponent(JComponent component, int x, int y){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        this.add(component, gbc);
    }

    public String getNameProcess(){
        return inputNameProcess.getText();
    }

    public BigInteger getTimeProcess(){
        BigInteger timeProcess = new BigInteger("0");
        try {
            timeProcess = new BigInteger(this.inputTimeProcess.getText());
        }catch (NumberFormatException numberFormatException){
            System.out.println("Error al convertir un número tan grande");

        }
        return timeProcess;
    }

    public boolean getIsBlocked(){
        return yesButton.isSelected();
    }

    public BigInteger getPriority(){
        BigInteger priority = new BigInteger("0");
        try {
            priority = new BigInteger((this.inputPriority.getText()));
        }catch (NumberFormatException numberFormatException){
            System.out.println("Error al convertir un número tan grande prioridad ");
        }
        return priority;
    }

    public boolean getIsSuspended(){
        return this.yesButtonSuspended.isSelected();
    }

    public void cleanFields(){
        this.inputNameProcess.setText("");
        this.inputTimeProcess.setText("");
        this.yesButton.setSelected(true);
        this.inputPriority.setText("");
        this.yesButtonSuspended.setSelected(true);
    }
    
}
