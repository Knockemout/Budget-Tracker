package ui;

import models.Expense;
import utils.DataManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class ExpenseTable extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton deleteButton;
    private SummaryPanel summaryPanel;


    public ExpenseTable(SummaryPanel summaryPanel) {
        this.summaryPanel = summaryPanel;
        setLayout(new BorderLayout());



        tableModel = new DefaultTableModel(new String[]{"Amount", "Category", "Description", "Date"}, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        deleteButton = new JButton("Delete Expense");
        deleteButton.addActionListener(this::deleteSelectedExpense);
        add(deleteButton, BorderLayout.SOUTH);

        refreshTable();
    }

    public void refreshTable() {
        tableModel.setRowCount(0);
        List<Expense> expenses = DataManager.getAllExpenses();
        for (Expense e : expenses) {
            tableModel.addRow(new Object[]{e.getAmount(), e.getCategory(), e.getDescription(), e.getDate()});
        }
    }

    private void deleteSelectedExpense(ActionEvent e) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            DataManager.deleteExpense(selectedRow);
            refreshTable();
            summaryPanel.refreshSummary();
            JOptionPane.showMessageDialog(this, "Expense deleted!");
        } else {
            JOptionPane.showMessageDialog(this, "Select an expense to delete.");
        }
    }
}
