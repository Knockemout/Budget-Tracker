package utils;

import models.Expense;
import models.Income;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataManager {
    private static final String EXPENSE_FILE = "expenses.dat";
    private static final String INCOME_FILE = "incomes.dat";

    public static void addExpense(Expense expense) {
        List<Expense> expenses = getAllExpenses();
        expenses.add(expense);
        saveData(EXPENSE_FILE, expenses);
    }

    public static void deleteExpense(int index) {
        List<Expense> expenses = getAllExpenses();
        if (index >= 0 && index < expenses.size()) {
            expenses.remove(index);
            saveData(EXPENSE_FILE, expenses);
        }
    }

    public static List<Expense> getAllExpenses() {
        return (List<Expense>) loadData(EXPENSE_FILE);
    }

    public static void addIncome(Income income) {
        List<Income> incomes = getAllIncomes();
        incomes.add(income);
        saveData(INCOME_FILE, incomes);
    }

    public static void deleteIncome(int index) {
        List<Income> incomes = getAllIncomes();
        if (index >= 0 && index < incomes.size()) {
            incomes.remove(index);
            saveData(INCOME_FILE, incomes);
        }
    }

    public static List<Income> getAllIncomes() {
        return (List<Income>) loadData(INCOME_FILE);
    }

    public static double getTotalIncome() {
        return getAllIncomes().stream().mapToDouble(Income::getAmount).sum();
    }

    public static double getTotalExpenses() {
        return getAllExpenses().stream().mapToDouble(Expense::getAmount).sum();
    }

    private static void saveData(String file, Object data) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Object loadData(String file) {
        File f = new File(file);
        if (!f.exists()) return new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }

    public static void clearAllExpenses() {
        saveData(EXPENSE_FILE, new ArrayList<Expense>());
    }

    public static void clearAllIncomes() {
        saveData(INCOME_FILE, new ArrayList<Income>());
    }


}
