package ui;

import models.Income;
import utils.DataManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class IncomeTable extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton deleteButton;
    private SummaryPanel summaryPanel;

    public IncomeTable(SummaryPanel summaryPanel) {
        this.summaryPanel = summaryPanel;
        setLayout(new BorderLayout());


        tableModel = new DefaultTableModel(new String[]{"Amount", "Source", "Date"}, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        deleteButton = new JButton("Delete Income");
        deleteButton.addActionListener(this::deleteSelectedIncome);
        add(deleteButton, BorderLayout.SOUTH);

        refreshTable();
    }

    public void refreshTable() {
        tableModel.setRowCount(0);
        List<Income> incomes = DataManager.getAllIncomes();
        for (Income i : incomes) {
            tableModel.addRow(new Object[]{i.getAmount(), i.getSource(), i.getDate()});
        }
    }

    private void deleteSelectedIncome(ActionEvent e) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            DataManager.deleteIncome(selectedRow);
            refreshTable();
            summaryPanel.updateSummary();
            JOptionPane.showMessageDialog(this, "Income deleted!");
        } else {
            JOptionPane.showMessageDialog(this, "Select an income to delete.");
        }
    }
}
