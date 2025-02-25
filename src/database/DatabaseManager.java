package database;

import models.Expense;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:sqlite:database/expenses.db";

    public static Connection connect() {
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(DB_URL);
        } catch (ClassNotFoundException e) {
            System.out.println("SQLite JDBC Driver not found. Make sure it's added to the project.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Database connection failed.");
            e.printStackTrace();
        }
        return conn;
    }

    public static void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS expenses ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "amount REAL, category TEXT, description TEXT, date TEXT)";
        try (Connection conn = connect();
             Statement stmt = conn != null ? conn.createStatement() : null) {
            if (stmt != null) {
                stmt.execute(sql);
            } else {
                System.out.println("Database connection failed, table not created.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addExpense(Expense expense) {
    }

    public static List<Expense> getAllExpenses() {
    return getAllExpenses();
    }
}
