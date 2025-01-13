package Workshpo_project;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

import java.util.ArrayList;
import java.util.List;

class BankAccount {
    private String accountNumber;
    private double balance;
    private List<String> transactionHistory;
    public BankAccount(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
        this.transactionHistory = new ArrayList<>();
    }
    public String getAccountNumber() {
        return accountNumber;
    }
    public double getBalance() {
        return balance;
    }
    public void deposit(double amount) {
        this.balance += amount;
        transactionHistory.add("Deposited: $" + amount);
    }
    public boolean withdraw(double amount) {
        if (amount <= balance) {
            this.balance -= amount;
            transactionHistory.add("Withdrew: $" + amount);
            return true;
        } else {
            return false;
        }
    }
    public List<String> getTransactionHistory() {
        return transactionHistory;
    }
}
class User {
    private String username;
    private String password;
    private BankAccount bankAccount;
    public User(String username, String password, BankAccount bankAccount) {
        this.username = username;
        this.password = password;
        this.bankAccount = bankAccount;
    }

    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public BankAccount getBankAccount() {
        return bankAccount;
    }
}
class BankSystem {
    private Map<String, User> users;
    private Map<String, BankAccount> accounts;
    private Scanner scanner;
    public BankSystem() {
        this.users = new HashMap<>();
        this.accounts = new HashMap<>();
        this.scanner = new Scanner(System.in);
    }
    public void addAccount(String username, String password, BankAccount bankAccount) {
        User user = new User(username, password, bankAccount);
        users.put(username, user);
        accounts.put(bankAccount.getAccountNumber(), bankAccount);
    }
    public boolean authenticate(String username, String password) {
        User user = users.get(username);
        return user != null && user.getPassword().equals(password);
    }
    public void deposit(String accountNumber, double amount) {
        BankAccount account = accounts.get(accountNumber);
        if (account != null) {
            account.deposit(amount);
            System.out.println("Deposit Successful!");
        } else {
            System.out.println("Account not found.");
        }
    }
    public void withdraw(String accountNumber, double amount) {
        BankAccount account = accounts.get(accountNumber);
        if (account != null) {
            if (account.withdraw(amount)) {
                System.out.println("Withdrawal Successful!");
            } else {
                System.out.println("Insufficient balance.");
            }
        } else {
            System.out.println("Account not found.");
        }
    }
    public void viewBalance(String accountNumber) {
        BankAccount account = accounts.get(accountNumber);
        if (account != null) {
            System.out.println("Account Balance: $" + account.getBalance());
        } else {
            System.out.println("Account not found.");
        }
    }
    public void transactionHistory(String accountNumber) {
        BankAccount account = accounts.get(accountNumber);
        if (account != null) {
            System.out.println("Transaction History:");
            for (String transaction : account.getTransactionHistory()) {
                System.out.println(transaction);
            }
        } else {
            System.out.println("Account not found.");
        }
    }
    public void adminPanel() {
        System.out.println("Admin Panel:");
        for (String username : users.keySet()) {
            System.out.println("User: " + username + " | Account Number: " + users.get(username).getBankAccount().getAccountNumber());
        }
    }
    public void start() {
        System.out.println("Welcome to the Bank System!");
        while (true) {
            System.out.println("1. Login");
            System.out.println("2. Admin Panel");
            System.out.println("3. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();  
            switch (choice) {
                case 1:
                    System.out.print("Enter Username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter Password: ");
                    String password = scanner.nextLine();
                    if (authenticate(username, password)) {
                        System.out.println("Login Successful!");
                        BankAccount account = users.get(username).getBankAccount();
                        while (true) {
                            System.out.println("1. Deposit");
                            System.out.println("2. Withdraw");
                            System.out.println("3. View Balance");
                            System.out.println("4. Transaction History");
                            System.out.println("5. Logout");
                            int userChoice = scanner.nextInt();
                            scanner.nextLine();  // Consume newline
                            switch (userChoice) {
                                case 1:
                                    System.out.print("Enter amount to deposit: ");
                                    double depositAmount = scanner.nextDouble();
                                    deposit(account.getAccountNumber(), depositAmount);
                                    break;
                                case 2:
                                    System.out.print("Enter amount to withdraw: ");
                                    double withdrawAmount = scanner.nextDouble();
                                    withdraw(account.getAccountNumber(), withdrawAmount);
                                    break;
                                case 3:
                                    viewBalance(account.getAccountNumber());
                                    break;
                                case 4:
                                    transactionHistory(account.getAccountNumber());
                                    break;
                                case 5:
                                    System.out.println("Logging out...");
                                    return;
                                default:
                                    System.out.println("Invalid choice.");
                            }
                        }
                    } else {
                        System.out.println("Invalid credentials.");
                    }
                    break;
                case 2:
                    adminPanel();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
    public static void main(String[] args) {
        BankSystem bankSystem = new BankSystem();
        BankAccount account1 = new BankAccount("123456", 10000.00);
        BankAccount account2 = new BankAccount("789012", 22200.00);
        bankSystem.addAccount("User1", "password123", account1);
        bankSystem.addAccount("User2", "password456", account2);
        bankSystem.start();
    }
}

