import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

class UserAccount {
    static void registerAccount() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("---------------------------");
        System.out.println("Please provide your details:");
        System.out.print("Name: ");
        ATM.userName = scanner.nextLine();
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        System.out.print("Account Number: ");
        ATM.accountNumber = scanner.nextLine();
        System.out.println("Registration successful!");
        System.out.println("---------------------------");
        ATM.showOptions();
        handleLogin(username, password);
    }

    static void handleLogin(String username, String password) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Login");
            System.out.println("2. Exit");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            if (choice == 1) {
                ATM.showOptions();
                break;
            } else if (choice == 2) {
                System.exit(0);
            } else {
                System.out.println("Invalid option. Please try again.");
            }
        }
    }
}

class BankingOperations {
    static void withdrawFunds() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("---------------");
        System.out.print("Enter amount to withdraw: ");
        int amount = scanner.nextInt();
        if (amount <= ATM.balance) {
            ATM.balance -= amount;
            ATM.transactionHistory.add("Withdraw: Rs " + amount);
            System.out.println("Withdrawal of Rs " + amount + " successful.");
        } else {
            System.out.println("Insufficient balance for withdrawal.");
        }
        System.out.println("---------------");
        ATM.showOptions();
    }

    static void depositFunds() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("---------------");
        System.out.print("Enter amount to deposit: ");
        int amount = scanner.nextInt();
        ATM.balance += amount;
        ATM.transactionHistory.add("Deposit: Rs " + amount);
        System.out.println("Deposit of Rs " + amount + " successful.");
        System.out.println("---------------");
        ATM.showOptions();
    }

    static void transferFunds() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter recipient's name: ");
        String recipient = scanner.nextLine();
        System.out.print("Enter recipient's account number: ");
        int accountNumber = scanner.nextInt();
        System.out.print("Enter amount to transfer: ");
        int amount = scanner.nextInt();
        if (amount <= ATM.balance) {
            ATM.balance -= amount;
            ATM.transactionHistory.add("Transferred Rs " + amount + " to account " + accountNumber);
            System.out.println("Transfer of Rs " + amount + " to account " + accountNumber + " successful.");
        } else {
            System.out.println("Insufficient balance for transfer.");
        }
    }
}

class BalanceCheck {
    static void displayBalance() {
        System.out.println("---------------");
        System.out.println("Current Balance: Rs " + ATM.balance);
        System.out.println("---------------");
        ATM.showOptions();
    }
}

class TransactionHistory {
    static void showHistory() {
        System.out.println("---------------");
        System.out.println("Transaction History:");
        if (ATM.transactionHistory.isEmpty()) {
            System.out.println("No transactions recorded.");
        } else {
            for (String record : ATM.transactionHistory) {
                System.out.println(record);
            }
        }
        System.out.println("---------------");
        ATM.showOptions();
    }
}

public class ATM {
    public static String userName;
    public static int balance = 0;
    public static String accountNumber;
    public static ArrayList<String> transactionHistory = new ArrayList<>();

    static void showOptions() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome " + userName + " to the ATM System");
        System.out.println("---------------------");
        System.out.println("Select an option:");
        System.out.println("1. Withdraw");
        System.out.println("2. Deposit");
        System.out.println("3. Transfer");
        System.out.println("4. Check Balance");
        System.out.println("5. Transaction History");
        System.out.println("6. Exit");
        System.out.print("Enter choice: ");
        try {
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    BankingOperations.withdrawFunds();
                    break;
                case 2:
                    BankingOperations.depositFunds();
                    break;
                case 3:
                    BankingOperations.transferFunds();
                    break;
                case 4:
                    BalanceCheck.displayBalance();
                    break;
                case 5:
                    TransactionHistory.showHistory();
                    break;
                case 6:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
                    showOptions();
                    break;
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next(); // Clear invalid input
            showOptions();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the ATM Interface");
        System.out.println("--------------------------");
        System.out.println("1. Register");
        System.out.println("2. Exit");
        System.out.print("Enter choice: ");
        try {
            int choice = scanner.nextInt();
            if (choice == 1) {
                UserAccount.registerAccount();
            } else if (choice == 2) {
                System.exit(0);
            } else {
                System.out.println("Invalid choice. Please select an option from the menu.");
                main(args);
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next(); // Clear invalid input
            main(args);
        }
    }
}
