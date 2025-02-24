package ui;

import models.Expense;
import utils.DataManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ExpenseTable extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;

    public ExpenseTable() {
        setLayout(new BorderLayout());
        tableModel = new DefaultTableModel(new String[]{"Amount", "Category", "Description", "Date"}, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);
        refreshTable();
    }

    public void refreshTable() {
        tableModel.setRowCount(0);
        List<Expense> expenses = DataManager.getAllExpenses();
        for (Expense e : expenses) {
            tableModel.addRow(new Object[]{e.getAmount(), e.getCategory(), e.getDescription(), e.getDate()});
        }
    }
}
