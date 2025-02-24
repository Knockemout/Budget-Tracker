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

        Font globalFont = new Font("Arial", Font.PLAIN, 14);
        UIManager.put("Label.font", globalFont);
        UIManager.put("Button.font", new Font("Arial", Font.BOLD, 14));
        UIManager.put("TextField.font", globalFont);
        UIManager.put("ComboBox.font", globalFont);

        getContentPane().setBackground(new Color(240, 240, 240));


        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        formPanel.setBackground(Color.WHITE);

        this.expenseTable = table;

        setTitle("Add Expense");
        setSize(300, 250);
        setLayout(new GridLayout(5, 2));

        add(new JLabel("Amount:"));
        amountField = new JTextField();
        add(amountField);

        add(new JLabel("Category:"));
        String[] categories = {"Food", "Transport", "Clothing", "Laundary", "Hospital", "Entertainment", "Other"};
        categoryBox = new JComboBox<>(categories);
        add(categoryBox);

        add(new JLabel("Description:"));
        descriptionField = new JTextField();
        add(descriptionField);

        saveButton = new JButton("Save");
        saveButton.setBackground(new Color(50, 150, 250));
        saveButton.setForeground(Color.WHITE);
        saveButton.setFocusPainted(false);
        saveButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        saveButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                saveButton.setBackground(new Color(30, 130, 230));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                saveButton.setBackground(new Color(50, 150, 250));
            }
        });

        add(saveButton);

        saveButton.addActionListener(e -> saveExpense());

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
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

            JOptionPane.showMessageDialog(this, "Expense Added!");
            dispose();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid amount!");
        }
    }
}
