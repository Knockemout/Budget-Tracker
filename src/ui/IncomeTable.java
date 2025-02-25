package ui;

import models.Income;
import utils.DataManager;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
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
        setBackground(new Color(245, 245, 245));

        JLabel titleLabel = new JLabel("📈 Income Table", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        titleLabel.setForeground(new Color(34, 139, 34));
        add(titleLabel, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new String[]{"Amount ($)", "Source", "Date"}, 0);
        table = new JTable(tableModel);
        styleTable();

        add(new JScrollPane(table), BorderLayout.CENTER);

        deleteButton = new JButton("🗑 Delete Income");
        deleteButton.setBackground(new Color(200, 0, 0));
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setFocusPainted(false);
        deleteButton.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));

        deleteButton.addActionListener(this::deleteSelectedIncome);
        add(deleteButton, BorderLayout.SOUTH);

        refreshTable();
    }

    private void styleTable() {
        table.setFont(new Font("SansSerif", Font.PLAIN, 14));
        table.setRowHeight(25);
        table.setGridColor(Color.LIGHT_GRAY);

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("SansSerif", Font.BOLD, 14));
        header.setBackground(new Color(60, 60, 60));
        header.setForeground(Color.WHITE);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
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
