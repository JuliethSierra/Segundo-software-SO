/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author julie
 */
public class PanelTableProcess extends JPanel{
    
    private DefaultTableModel modelTable;
    private JTable tableProcess;
    private JScrollPane scroll;
    private TableColumn column;
    
    public PanelTableProcess(){
        initComponents();
        setVisible(true);
    }
    
    private void initComponents(){
        setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
        setBackground(Color.WHITE);
        String[] headers  = ConstantsGUI.headers;
        modelTable = new DefaultTableModel();
        modelTable.setColumnIdentifiers(headers);
        
        tableProcess = new JTable();
        tableProcess.setModel(modelTable);
        tableProcess.getTableHeader().setReorderingAllowed(false);
        tableProcess.getTableHeader().setBackground(Color.decode("#4a4e69"));
        tableProcess.getTableHeader().setForeground(Color.WHITE);
        tableProcess.getTableHeader().setFont(ConstantsGUI.FONT_TABLE_HEADER);
        tableProcess.setFillsViewportHeight(true);
        tableProcess.setFont(ConstantsGUI.FONT_TABLE);
        tableProcess.setBackground(Color.decode("#F2E9E4"));
        tableProcess.setOpaque(true);
        tableProcess.setRowHeight(25);

        
        scroll = new JScrollPane(tableProcess);
        scroll.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.decode("#204051")),
        "Procesos Existentes", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, ConstantsGUI.FONT_TABLE_HEADER, Color.decode("#204051")));
        scroll.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.add(scroll, BorderLayout.PAGE_END);
        setBorder(null);
    }

    public void setTableProcess(DefaultTableModel defaultTableModel){
        this.modelTable = defaultTableModel;
        this.tableProcess.setModel(defaultTableModel);
    }

    public int getIndexDataProcess(){
       return this.tableProcess.getSelectedRow();
    }

}
