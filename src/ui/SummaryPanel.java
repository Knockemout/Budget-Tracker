package ui;

import utils.DataManager;

import javax.swing.*;
import java.awt.*;

public class SummaryPanel extends JPanel {
    private JLabel incomeLabel, expenseLabel, balanceLabel;

    public SummaryPanel() {
        setLayout(new GridLayout(3, 1));

        incomeLabel = new JLabel("Total Income: $0.00");
        expenseLabel = new JLabel("Total Expenses: $0.00");
        balanceLabel = new JLabel("Remaining Balance: $0.00");

        add(incomeLabel);
        add(expenseLabel);
        add(balanceLabel);

        refreshSummary();
    }

    public void refreshSummary() {
        double totalIncome = DataManager.getTotalIncome();
        double totalExpenses = DataManager.getTotalExpenses();
        double balance = totalIncome - totalExpenses;

        incomeLabel.setText("Total Income: $" + totalIncome);
        expenseLabel.setText("Total Expenses: $" + totalExpenses);
        balanceLabel.setText("Remaining Balance: $" + balance);
    }
}
