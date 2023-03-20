/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
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
public class PanelReadyReport extends JPanel{
    private DefaultTableModel modelTableReport;
    private JTable tableProcessReport;
    private JScrollPane scrollReport;
    private TableColumn columnReport;
    
    public PanelReadyReport(){
        initComponents();
        setVisible(true);
    }
    
        private void initComponents(){
        setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
        setBackground(Color.WHITE);
        String[] headers  = ConstantsGUI.headers;
        modelTableReport = new DefaultTableModel();
        modelTableReport.setColumnIdentifiers(headers);
        
        tableProcessReport = new JTable();
        tableProcessReport.setModel(modelTableReport);
        tableProcessReport.getTableHeader().setReorderingAllowed(false);
        tableProcessReport.getTableHeader().setBackground(Color.decode("#4a4e69"));
        tableProcessReport.getTableHeader().setForeground(Color.WHITE);
        tableProcessReport.getTableHeader().setFont(ConstantsGUI.FONT_TABLE_HEADER);
        tableProcessReport.setFillsViewportHeight(true);
        tableProcessReport.setFont(ConstantsGUI.FONT_TABLE);
        tableProcessReport.setBackground(Color.decode("#F2E9E4"));
        tableProcessReport.setOpaque(true);
        tableProcessReport.setRowHeight(25);
        
        scrollReport = new JScrollPane(tableProcessReport);
        scrollReport.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.decode("#204051")),
        "Procesos Listos  ", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, ConstantsGUI.FONT_TABLE_HEADER, Color.decode("#204051")));
        scrollReport.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.add(scrollReport, BorderLayout.PAGE_END);
        setBorder(null);
    }

    public void setTableProcess(DefaultTableModel defaultTableModel){
        this.modelTableReport = defaultTableModel;
        this.tableProcessReport.setModel(defaultTableModel);
    }
}
