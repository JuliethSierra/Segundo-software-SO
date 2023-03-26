package views;

import javax.swing.*;
import javax.swing.text.DefaultFormatter;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.math.BigInteger;

public class JDModifyProcess extends  JDialog{
    private ButtonActions modify, cancel;
    private JTextField inputNameProcess, inputPriority, inputTimeProcess;
    private JLabel nameProcess, timeProcess, blockProcess, suspendedProcess, priorityProcess;

    private JRadioButton yesButton, noButton, yesButtonSuspended, noButtonSuspended;
    private ButtonGroup groupRadioButton, groupRadioButtonSuspended;
    private JPanel panelGroup;

    public JDModifyProcess(ActionListener listener, KeyListener keyListener){
        this.setModal(true);
        this.setTitle("Modificar Proceso");
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
        panelGroup = new JPanel();
        panelGroup.setBackground(Color.decode("#C9ADA7"));
        panelGroup.add(yesButtonSuspended);
        panelGroup.add(noButtonSuspended);
        addComponent(panelGroup, 1, 4);

        modify = new ButtonActions("Modificar");
        modify.addActionListener(listener);
        modify.setActionCommand("Modificado");
        modify.setPreferredSize(new Dimension(150, 35));
        addComponent(modify,0,5);

        cancel = new ButtonActions("Cancelar");
        cancel.addActionListener(listener);
        cancel.setActionCommand("CancelarMod");
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

    public Object getTimeProcess(){
        return inputTimeProcess.getText();
    }

    public boolean getIsBlocked(){
        return yesButton.isSelected();
    }

    public BigInteger getModifyPriority(){
        BigInteger priority = new BigInteger("0");
        try {
            priority = new BigInteger(this.inputPriority.getText());
        }catch (NumberFormatException numberFormatException){
            System.out.println("Error en modificar");
        }
        return priority;
    }
    public boolean getModifyIsSuspended(){
        return yesButtonSuspended.isSelected();
    }

    public void setInputNameProcess(String inputNameProcess) {
        this.inputNameProcess.setText(inputNameProcess);
    }

    public void setInputTimeProcess(BigInteger inputTimeProcess) {
        this.inputTimeProcess.setText(inputTimeProcess.toString());
    }

    public void setRadioButton(boolean isblock){
        if(isblock) {
            yesButton.isSelected();
        }else
            noButton.isSelected();
    }

    public void setRadioButtonSuspended(boolean isSuspended){
        if(isSuspended) {
            yesButton.isSelected();
        }else
            noButton.isSelected();
    }

    public void cleanFields(){
        this.inputNameProcess.setText("");
        this.inputTimeProcess.setText("0");
        this.yesButton.setSelected(true);
        this.inputPriority.setText("");
        this.yesButtonSuspended.setSelected(true);
    }

}
