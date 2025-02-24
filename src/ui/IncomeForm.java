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
        setSize(300, 200);
        setLayout(new GridLayout(4, 2));

        add(new JLabel("Amount:"));
        amountField = new JTextField();
        add(amountField);

        add(new JLabel("Source:"));
        sourceField = new JTextField();
        add(sourceField);

        saveButton = new JButton("Save");
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
