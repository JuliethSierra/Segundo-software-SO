/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package views;

import models.Process;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.math.BigInteger;

/**
 *
 * @author julie
 */
public class JDCreateRelation extends JDialog {
    private ButtonActions create, cancel;
    private JLabel firstElement, secondElement;
    private JComboBox<String> firstElementCombo, secondElementCombo;


    public JDCreateRelation(ActionListener listener){
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
        initComponents(listener);
    }
    
    private void initComponents(ActionListener listener){
        firstElement = new JLabel("Primer proceso");
        firstElement.setFont(ConstantsGUI.FONT_TITLE_INPUTS);
        addComponent(firstElement, 0, 0);

        this.firstElementCombo = new JComboBox<>();
        this.firstElementCombo.setSize(150, 50);
        this.firstElementCombo.setPreferredSize(new Dimension(150,30));
        this.firstElementCombo.setBackground(Color.WHITE);
        this.firstElementCombo.setFont(ConstantsGUI.FONT_INPUTS);
        addComponent(this.firstElementCombo, 0, 1);

        
        secondElement = new JLabel("Segundo proceso");
        secondElement.setFont(ConstantsGUI.FONT_TITLE_INPUTS);
        addComponent(secondElement, 0, 2);

        this.secondElementCombo = new JComboBox<>();
        this.secondElementCombo.setSize(100, 50);
        this.secondElementCombo.setPreferredSize(new Dimension(100,30));
        this.secondElementCombo.setBackground(Color.WHITE);
        this.secondElementCombo.setFont(ConstantsGUI.FONT_INPUTS);
        addComponent(this.secondElementCombo, 0, 3);



        create = new ButtonActions("Añadir");
        create.addActionListener(listener);
        create.setActionCommand("Añadir Relacion");
        create.setPreferredSize(new Dimension(150, 35));
        addComponent(create,0,5);

        cancel = new ButtonActions("Cancelar");
        cancel.addActionListener(listener);
        cancel.setActionCommand("Cancelar Relacion");
        cancel.setPreferredSize(new Dimension(150, 35));
        addComponent(cancel,1,5);
        
    }

    public void setValuesCombos(String[] process){
        this.firstElementCombo.setModel(new DefaultComboBoxModel<>(process));
        this.secondElementCombo.setModel(new DefaultComboBoxModel<>(process));
    }

        public void addComponent(JComponent component, int x, int y){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        this.add(component, gbc);
    }

    public String getSelectedElementFirstCombo(){
        return (String) this.firstElementCombo.getSelectedItem();
    }

    public String getSelectedElementSecondCombo(){
        return (String) this.secondElementCombo.getSelectedItem();
    }
    
}
