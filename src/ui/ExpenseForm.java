package ui;

import models.Expense;
import utils.DataManager;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class ExpenseForm extends JFrame {
    private JTextField amountField, descriptionField;
    private JComboBox<String> categoryBox;
    private JButton saveButton;
    private ExpenseTable expenseTable;

    public ExpenseForm(ExpenseTable table, SummaryPanel summaryPanel) {
        this.expenseTable = table;

        setTitle("Add Expense");
        setSize(350, 260);
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

        JLabel categoryLabel = new JLabel("Category:");
        categoryLabel.setFont(fieldFont);
        gbc.gridx = 0; gbc.gridy = 1;
        add(categoryLabel, gbc);

        String[] categories = {"Food", "Transport", "Clothing", "Laundry", "Hospital", "Entertainment", "Other"};
        categoryBox = new JComboBox<>(categories);
        categoryBox.setFont(fieldFont);
        gbc.gridx = 1; gbc.gridy = 1;
        add(categoryBox, gbc);

        JLabel descriptionLabel = new JLabel("Description:");
        descriptionLabel.setFont(fieldFont);
        gbc.gridx = 0; gbc.gridy = 2;
        add(descriptionLabel, gbc);

        descriptionField = new JTextField();
        descriptionField.setFont(fieldFont);
        gbc.gridx = 1; gbc.gridy = 2;
        add(descriptionField, gbc);

        saveButton = new JButton("💾 Save Expense");
        saveButton.setBackground(new Color(244, 67, 54)); // Red Button
        saveButton.setForeground(Color.WHITE);
        saveButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        saveButton.setFocusPainted(false);
        saveButton.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));

        gbc.gridwidth = 2;
        gbc.gridx = 0; gbc.gridy = 3;
        add(saveButton, gbc);

        saveButton.addActionListener(e -> saveExpense());

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public static void clearFields() {

    }

    private void saveExpense() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            String category = (String) categoryBox.getSelectedItem();
            String description = descriptionField.getText();
            Date date = new Date();

            Expense expense = new Expense(amount, category, description, date);
            DataManager.addExpense(expense);
            expenseTable.refreshTable();

            JOptionPane.showMessageDialog(this, "✅ Expense Added Successfully!");
            dispose();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "❌ Invalid amount! Please enter a valid number.");
        }
    }
}
