package models;

import java.io.Serializable;
import java.util.Date;

public class Income implements Serializable {
    private double amount;
    private String source;
    private Date date;

    public Income(double amount, String source, Date date) {
        this.amount = amount;
        this.source = source;
        this.date = date;
    }

    public double getAmount() { return amount; }
    public String getSource() { return source; }
    public Date getDate() { return date; }
}
