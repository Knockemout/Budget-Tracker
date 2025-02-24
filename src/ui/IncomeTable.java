package ui;

import models.Income;
import utils.DataManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class IncomeTable extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;

    public IncomeTable() {
        setLayout(new BorderLayout());
        tableModel = new DefaultTableModel(new String[]{"Amount", "Source", "Date"}, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);
        refreshTable();
    }

    public void refreshTable() {
        tableModel.setRowCount(0);
        List<Income> incomes = DataManager.getAllIncomes();
        for (Income i : incomes) {
            tableModel.addRow(new Object[]{i.getAmount(), i.getSource(), i.getDate()});
        }
    }
}
