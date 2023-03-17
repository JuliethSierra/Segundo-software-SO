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
public class PanelMenu extends JPanel{
    
    private ButtonMenu addProcess;
    private ButtonMenu modifyProcess;
    private ButtonMenu destroyProcess;
    private ButtonMenu reports;
    private ButtonMenu sendProcess;
    private ButtonMenu exit;
    private JLabel titleMenu;
    
    public PanelMenu(ActionListener listener){
        this.setLayout(new GridBagLayout());
        this.setBackground(Color.decode("#4a4e69"));
        this.setPreferredSize(new Dimension(350,80));
        this.setMaximumSize(new Dimension(350,80));
        this.setMinimumSize(new Dimension(350,80));
        initComponents(listener);
        this.setVisible(true);
    }
    
    private void initComponents(ActionListener listener){
        titleMenu = new JLabel("Procesos");
        titleMenu.setForeground(Color.WHITE);
        titleMenu.setFont(ConstantsGUI.FONT_MENU_TITLE);
        addComponent(titleMenu, 0, 1);
        
        addProcess = new ButtonMenu("Crear");
        addProcess.addActionListener(listener);
        addProcess.setActionCommand("Agregar");
        addComponent(addProcess, 0, 2);
        
        modifyProcess = new ButtonMenu("Modificar");
        modifyProcess.addActionListener(listener);
        modifyProcess.setActionCommand("Modificar");
        addComponent(modifyProcess, 0, 3);
                
        destroyProcess = new ButtonMenu("Destruir");
        destroyProcess.addActionListener(listener);
        destroyProcess.setActionCommand("Eliminar");
        addComponent(destroyProcess, 0, 4);
        
        reports = new ButtonMenu("Reportes");
        reports.addActionListener(listener);
        reports.setActionCommand("Reportes");
        addComponent(reports, 0, 5);
        
        sendProcess = new ButtonMenu("Iniciar Simulación");
        sendProcess.addActionListener(listener);
        sendProcess.setActionCommand("Enviar");
        addComponent(sendProcess, 0, 6);
        
        exit = new ButtonMenu("Salir");
        exit.addActionListener(listener);
        exit.setActionCommand("Salir");
        addComponent(exit, 0, 7);
    }
    
    public void addComponent(JComponent component, int x, int y){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        this.add(component, gbc);
    }
}
