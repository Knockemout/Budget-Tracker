package ui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    public MainFrame() {
        setTitle("Budget Tracker");
        setSize(700, 500);
        setLayout(new BorderLayout());

        SummaryPanel summaryPanel = new SummaryPanel();
        ExpenseTable expenseTable = new ExpenseTable();
        IncomeTable incomeTable = new IncomeTable();

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

        add(summaryPanel, BorderLayout.NORTH);
        add(tablesPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
