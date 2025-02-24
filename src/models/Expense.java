package models;

import java.io.Serializable;
import java.util.Date;

public class Expense implements Serializable {
    private double amount;
    private String category;
    private String description;
    private Date date;

    public Expense(double amount, String category, String description, Date date) {
        this.amount = amount;
        this.category = category;
        this.description = description;
        this.date = date;
    }

    public double getAmount() { return amount; }
    public String getCategory() { return category; }
    public String getDescription() { return description; }
    public Date getDate() { return date; }
}
