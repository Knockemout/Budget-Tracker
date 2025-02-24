package ui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    public MainFrame() {
        setTitle("Expense Tracker");
        setSize(600, 400);
        setLayout(new BorderLayout());

        ExpenseTable expenseTable = new ExpenseTable();
        add(expenseTable, BorderLayout.CENTER);

        JButton addExpenseBtn = new JButton("Add Expense");
        add(addExpenseBtn, BorderLayout.SOUTH);

        addExpenseBtn.addActionListener(e -> new ExpenseForm(expenseTable));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
