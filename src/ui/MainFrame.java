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
        tablesPanel.add(expenseTable);
        tablesPanel.add(incomeTable);

        JButton addExpenseBtn = new JButton("Add Expense");
        JButton addIncomeBtn = new JButton("Add Income");

        addExpenseBtn.addActionListener(e -> new ExpenseForm(expenseTable, summaryPanel));
        addIncomeBtn.addActionListener(e -> new IncomeForm(incomeTable, summaryPanel));

        JPanel buttonPanel = new JPanel();
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
