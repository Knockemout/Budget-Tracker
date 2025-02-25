package ui;

import javax.swing.*;
import java.awt.*;
import utils.DataManager;

public class MainFrame extends JFrame {
    public MainFrame() {
        setTitle("Budget Tracker");
        setSize(850, 500);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(240, 240, 240)); // Light Gray

        Font globalFont = new Font("SansSerif", Font.PLAIN, 14);
        UIManager.put("Label.font", globalFont);
        UIManager.put("Button.font", globalFont);
        UIManager.put("TextField.font", globalFont);
        UIManager.put("TextArea.font", globalFont);

        SummaryPanel summaryPanel = new SummaryPanel();
        ExpenseTable expenseTable = new ExpenseTable(summaryPanel);
        IncomeTable incomeTable = new IncomeTable(summaryPanel);

        JPanel tablesPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        tablesPanel.add(expenseTable);
        tablesPanel.add(incomeTable);
        tablesPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton addExpenseBtn = createStyledButton("➕ Add Expense", new Color(60, 150, 80));
        JButton addIncomeBtn = createStyledButton("💰 Add Income", new Color(60, 120, 200));

        addExpenseBtn.addActionListener(e -> new ExpenseForm(expenseTable, summaryPanel));
        addIncomeBtn.addActionListener(e -> new IncomeForm(incomeTable, summaryPanel));

        JButton clearAllButton = new JButton("Clear All Data");
        clearAllButton.setBackground(new Color(220, 20, 60));  // Crimson Red
        clearAllButton.setForeground(Color.WHITE);
        clearAllButton.setFocusPainted(false);
        clearAllButton.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));

        clearAllButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(
                    null,
                    "Are you sure you want to delete all expense and income records?",
                    "Confirm Delete",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                DataManager.clearAllExpenses();
                DataManager.clearAllIncomes();

                expenseTable.refreshTable();
                incomeTable.refreshTable();
                summaryPanel.refreshSummary();

                JOptionPane.showMessageDialog(null, "All records cleared successfully!");
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        buttonPanel.add(addExpenseBtn);
        buttonPanel.add(addIncomeBtn);
        buttonPanel.add(clearAllButton); // Add Clear All button
        buttonPanel.setBackground(Color.LIGHT_GRAY);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(buttonPanel, BorderLayout.SOUTH);



        buttonPanel.setBackground(new Color(230, 230, 230)); // Light Gray
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        add(summaryPanel, BorderLayout.NORTH);
        add(tablesPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        return button;
    }
}
