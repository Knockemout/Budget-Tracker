package ui;

import utils.DataManager;
import javax.swing.*;
import java.awt.*;

public class SummaryPanel extends JPanel {
    private JLabel totalIncomeLabel;
    private JLabel totalExpensesLabel;
    private JLabel balanceLabel;
    private JButton showTotalsButton;

    public SummaryPanel() {
        setLayout(new GridLayout(2, 2));
        Font globalFont = new Font("Arial", Font.BOLD, 15);
        UIManager.put("Label.font", globalFont);
        UIManager.put("Button.font", globalFont);
        UIManager.put("TextField.font", globalFont);
        UIManager.put("TextArea.font", globalFont);

        totalIncomeLabel = new JLabel("Total Income: $0.00");
        totalIncomeLabel.setForeground(new Color(76, 175, 80)); // Green Text
        totalExpensesLabel = new JLabel("Total Expenses: $0.00");
        totalExpensesLabel.setForeground(new Color(244, 67, 54)); // Red Text
        balanceLabel = new JLabel("Remaining Balance: $0.00");
        balanceLabel.setForeground(new Color(33, 150, 243));

        showTotalsButton = new JButton("Show Totals");
        showTotalsButton.addActionListener(e -> updateSummary());

        add(totalIncomeLabel);
        add(totalExpensesLabel);
        add(balanceLabel);
        add(showTotalsButton);
    }

    public void updateSummary() {
        double totalIncome = DataManager.getTotalIncome();
        double totalExpenses = DataManager.getTotalExpenses();
        double balance = totalIncome - totalExpenses;

        totalIncomeLabel.setText("Total Income: $" + totalIncome);
        totalExpensesLabel.setText("Total Expenses: $" + totalExpenses);
        balanceLabel.setText("Remaining Balance: $" + balance);
    }

    public void refreshSummary() {

    }
}
