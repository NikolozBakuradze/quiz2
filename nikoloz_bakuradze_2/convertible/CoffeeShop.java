package quizes.q2.nikoloz_bakuradze_2.convertible;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CoffeeShop {

    public static void main(String[] args) {
        int pricePerCoffee = 250; // tetri
        int numCoffeeSold = 100;
        int totalCostOfBeans = 15000; // tetri

        // Read expenses from file and calculate total other expenses
        int otherExpenses = readExpensesFromFile("expenses.txt");

        // Calculate profit
        double profitInLaris = calculateProfit(pricePerCoffee, numCoffeeSold, totalCostOfBeans, otherExpenses);

        System.out.println("Total profit: " + profitInLaris + " lari");
    }

    // Method to read expenses from file and calculate total other expenses
    private static int readExpensesFromFile(String fileName) {
        int totalExpenses = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" - ");
                if (parts.length == 2) {
                    String expenseName = parts[0].trim();
                    String expenseValue = parts[1].trim();
                    // Convert the value to integer
                    int expenseAmount = parseExpense(expenseValue);
                    // Add the expense to totalExpenses
                    totalExpenses += expenseAmount;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading expenses file: " + e.getMessage());
        }
        return totalExpenses;
    }

    // Method to parse expense values like "56 lari" or "87 tetri" into tetri
    private static int parseExpense(String expense) {
        String[] parts = expense.split(" ");
        if (parts.length == 2) {
            int amount = Integer.parseInt(parts[0]);
            String unit = parts[1].toLowerCase();
            if (unit.equals("lari")) {
                return amount * 100; // Convert lari to tetri
            } else if (unit.equals("tetri")) {
                return amount;
            }
        }
        return 0; // Return 0 if parsing fails
    }

    public static double calculateProfit(int pricePerCoffee, int numCoffeeSold, int totalCostOfBeans, int otherExpenses) {
        // Calculate total revenue
        double totalRevenue = pricePerCoffee * numCoffeeSold / 100.0; // Convert from tetri to lari

        // Calculate total cost (including beans cost and other expenses)
        double totalCost = totalCostOfBeans / 100.0 + otherExpenses / 100.0; // Convert from tetri to lari

        // Calculate profit
        double profitInLaris = totalRevenue - totalCost;

        return profitInLaris;
    }
}