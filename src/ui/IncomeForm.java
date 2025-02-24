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

        Font globalFont = new Font("Arial", Font.PLAIN, 14);
        UIManager.put("Label.font", globalFont);
        UIManager.put("Button.font", new Font("Arial", Font.BOLD, 14));
        UIManager.put("TextField.font", globalFont);
        UIManager.put("ComboBox.font", globalFont);

        getContentPane().setBackground(new Color(240, 240, 240));

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        formPanel.setBackground(Color.WHITE);



        setTitle("Add Income");
        setSize(300, 200);
        setLayout(new GridLayout(4, 2));

        add(new JLabel("Amount:"));
        amountField = new JTextField();
        add(amountField);

        add(new JLabel("Source:"));
        sourceField = new JTextField();
        add(sourceField);

        saveButton = new JButton("Save");
        saveButton.setBackground(new Color(50, 150, 250)); // Blue button
        saveButton.setForeground(Color.WHITE);
        saveButton.setFocusPainted(false);
        saveButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        saveButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                saveButton.setBackground(new Color(30, 130, 230)); // Darker blue on hover
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                saveButton.setBackground(new Color(50, 150, 250));
            }
        });

        add(saveButton);

        saveButton.addActionListener(e -> saveIncome());

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
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

            JOptionPane.showMessageDialog(this, "Income Added!");
            dispose();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid amount!");
        }
    }
}
