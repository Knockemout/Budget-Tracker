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
        setLayout(new GridLayout(2, 2, 10, 10));
        setBackground(new Color(245, 245, 245)); // Light Gray Background
        setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        Font labelFont = new Font("SansSerif", Font.BOLD, 14);

        totalIncomeLabel = createStyledLabel("Total Income: $0.00", new Color(34, 177, 76), labelFont);
        totalExpensesLabel = createStyledLabel("Total Expenses: $0.00", new Color(200, 0, 0), labelFont);
        balanceLabel = createStyledLabel("Remaining Balance: $0.00", new Color(0, 102, 204), labelFont);

        showTotalsButton = new JButton("💰 Show Totals");
        showTotalsButton.setBackground(new Color(60, 120, 200));
        showTotalsButton.setForeground(Color.WHITE);
        showTotalsButton.setFocusPainted(false);
        showTotalsButton.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));

        showTotalsButton.addActionListener(e -> updateSummary());

        add(totalIncomeLabel);
        add(totalExpensesLabel);
        add(balanceLabel);
        add(showTotalsButton);
    }

    private JLabel createStyledLabel(String text, Color color, Font font) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setForeground(color);
        label.setFont(font);
        return label;
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
        updateSummary();
    }
}
