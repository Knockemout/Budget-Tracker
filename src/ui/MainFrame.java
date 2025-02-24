package ui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    public MainFrame() {
        setTitle("Budget Tracker");
        setSize(850, 500);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(245, 245, 245));

        Font globalFont = new Font("Arial", Font.BOLD, 15);
        UIManager.put("Label.font", globalFont);
        UIManager.put("Button.font", globalFont);
        UIManager.put("TextField.font", globalFont);
        UIManager.put("TextArea.font", globalFont);



        SummaryPanel summaryPanel = new SummaryPanel();
        ExpenseTable expenseTable = new ExpenseTable(summaryPanel);
        IncomeTable incomeTable = new IncomeTable(summaryPanel);

        JPanel tablesPanel = new JPanel(new GridLayout(1, 2));
        tablesPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));


        tablesPanel.add(expenseTable);
        tablesPanel.add(incomeTable);

        JButton addExpenseBtn = new JButton("Add Expense");
        JButton addIncomeBtn = new JButton("Add Income");


        addExpenseBtn.setFocusPainted(false);
        addIncomeBtn.setFocusPainted(false);

        addExpenseBtn.setBackground(new Color(72, 133, 237)); // Google Blue
        addExpenseBtn.setForeground(Color.WHITE);
        addExpenseBtn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        addIncomeBtn.setBackground(new Color(60, 186, 84)); // Google Green
        addIncomeBtn.setForeground(Color.WHITE);
        addIncomeBtn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

// Add hover effect
        addExpenseBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                addExpenseBtn.setBackground(new Color(52, 113, 217));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                addExpenseBtn.setBackground(new Color(72, 133, 237));
            }
        });

        addIncomeBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                addIncomeBtn.setBackground(new Color(40, 166, 64));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                addIncomeBtn.setBackground(new Color(60, 186, 84));
            }
        });


        addExpenseBtn.addActionListener(e -> new ExpenseForm(expenseTable, summaryPanel));
        addIncomeBtn.addActionListener(e -> new IncomeForm(incomeTable, summaryPanel));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        buttonPanel.add(addExpenseBtn);
        buttonPanel.add(addIncomeBtn);
        buttonPanel.setBackground(Color.LIGHT_GRAY);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        add(summaryPanel, BorderLayout.NORTH);
        add(tablesPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
