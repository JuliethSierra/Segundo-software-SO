package views;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;

public class PanelDispatchedReport extends JPanel {

    private DefaultTableModel modelTableReport;
    private JTable tableProcessReport;
    private JScrollPane scrollReport;
    private TableColumn columnReport;
    public PanelDispatchedReport(){
        initComponents();
        setVisible(true);
    }

    private void initComponents(){
        setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
        setBackground(Color.WHITE);
        String[] headers  = {"Nombre", "Tiempo", "Bloqueo"};
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
                "Procesos Despachados", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, ConstantsGUI.FONT_TABLE_HEADER, Color.decode("#204051")));
        scrollReport.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.add(scrollReport, BorderLayout.PAGE_END);
        setBorder(null);
    }

    public void setTableProcess(DefaultTableModel defaultTableModel){
        this.modelTableReport = defaultTableModel;
        this.tableProcessReport.setModel(defaultTableModel);
    }
}
