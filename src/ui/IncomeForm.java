package ui;

import models.Income;
import utils.DataManager;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class IncomeForm extends JFrame {
    private JTextField amountField, sourceField;
    private JButton saveButton;
    private IncomeTable incomeTable;
    private SummaryPanel summaryPanel;

    public IncomeForm(IncomeTable table, SummaryPanel summary) {
        this.incomeTable = table;
        this.summaryPanel = summary;

        setTitle("Add Income");
        setSize(350, 220);
        setLayout(new GridBagLayout());
        setResizable(false);
        setLocationRelativeTo(null); // Center the form

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Font fieldFont = new Font("SansSerif", Font.PLAIN, 14);

        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setFont(fieldFont);
        gbc.gridx = 0; gbc.gridy = 0;
        add(amountLabel, gbc);

        amountField = new JTextField();
        amountField.setFont(fieldFont);
        gbc.gridx = 1; gbc.gridy = 0;
        add(amountField, gbc);

        JLabel sourceLabel = new JLabel("Source:");
        sourceLabel.setFont(fieldFont);
        gbc.gridx = 0; gbc.gridy = 1;
        add(sourceLabel, gbc);

        sourceField = new JTextField();
        sourceField.setFont(fieldFont);
        gbc.gridx = 1; gbc.gridy = 1;
        add(sourceField, gbc);

        saveButton = new JButton("💾 Save Income");
        saveButton.setBackground(new Color(76, 175, 80));
        saveButton.setForeground(Color.WHITE);
        saveButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        saveButton.setFocusPainted(false);
        saveButton.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));

        gbc.gridwidth = 2;
        gbc.gridx = 0; gbc.gridy = 2;
        add(saveButton, gbc);

        saveButton.addActionListener(e -> saveIncome());

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public static void clearFields() {
    }

    private void saveIncome() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            String source = sourceField.getText();
            Date date = new Date();

            Income income = new Income(amount, source, date);
            DataManager.addIncome(income);
            incomeTable.refreshTable();
            summaryPanel.refreshSummary();

            JOptionPane.showMessageDialog(this, "✅ Income Added Successfully!");
            dispose();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "❌ Invalid amount! Please enter a valid number.");
        }
    }
}
