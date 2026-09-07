import java.util.Scanner;

public class ATM {
    private Bank bank;
    private Scanner scanner;
    private Account currentAccount;

    public ATM(Bank bank) {
        this.bank = bank;
        scanner = new Scanner(System.in);
    }

    public void start() {

        System.out.println("================================");
        System.out.println("        WELCOME TO ATM");
        System.out.println("================================");

        if (!login()) {
            System.out.println("Too many incorrect attempts.");
            System.out.println("Account access denied.");
            return;
        }

        showMenu();
    }

    private boolean login() {

        int attempts = 0;

        while (attempts < 3) {

            System.out.print("Enter User ID: ");
            String userId = scanner.nextLine();

            System.out.print("Enter PIN: ");
            String pin = scanner.nextLine();

            Account account = bank.getAccount(userId);

            if (account != null && account.getPin().equals(pin)) {
                currentAccount = account;

                System.out.println("\nLogin successful!");
                System.out.println("Welcome, User " + userId);

                return true;
            }

            attempts++;

            System.out.println("Incorrect User ID or PIN.");
            System.out.println("Attempts remaining: " + (3 - attempts));
        }

        return false;
    }

    private void showMenu() {

        while (true) {

            System.out.println("\n================================");
            System.out.println("          ATM MENU");
            System.out.println("================================");
            System.out.println("1. Transaction History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");
            System.out.println("================================");

            System.out.print("Enter your choice: ");

            int choice;

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice.");
                continue;
            }

            switch (choice) {

                case 1:
                    showTransactionHistory();
                    break;

                case 2:
                    withdraw();
                    break;

                case 3:
                    deposit();
                    break;

                case 4:
                    transfer();
                    break;

                case 5:
                    System.out.println("\nThank you for using our ATM.");
                    System.out.println("Goodbye!");
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void showTransactionHistory() {

        System.out.println("\n========== TRANSACTION HISTORY ==========");

        if (currentAccount.getTransactions().isEmpty()) {
            System.out.println("No transactions in this session.");
        } else {

            for (Transaction transaction :
                    currentAccount.getTransactions()) {

                System.out.println(transaction);
            }
        }

        System.out.println("=========================================");
        System.out.println("Current Balance: ₹" +
                currentAccount.getBalance());
    }

    private void withdraw() {

        System.out.print("Enter withdrawal amount: ");

        double amount;

        try {
            amount = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount.");
            return;
        }

        if (amount <= 0) {
            System.out.println("Amount must be greater than zero.");
            return;
        }

        // Balance check
        if (amount > currentAccount.getBalance()) {
            System.out.println("Insufficient Funds");
            return;
        }

        currentAccount.withdraw(amount);

        currentAccount.addTransaction(
                new Transaction(
                        "WITHDRAW",
                        amount,
                        "Cash withdrawn"
                )
        );

        System.out.println("Withdrawal successful.");
        System.out.println("Amount: ₹" + amount);
        System.out.println("Remaining Balance: ₹" +
                currentAccount.getBalance());
    }

    private void deposit() {

        System.out.print("Enter deposit amount: ");

        double amount;

        try {
            amount = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount.");
            return;
        }

        if (amount <= 0) {
            System.out.println("Amount must be greater than zero.");
            return;
        }

        currentAccount.deposit(amount);

        currentAccount.addTransaction(
                new Transaction(
                        "DEPOSIT",
                        amount,
                        "Cash deposited"
                )
        );

        System.out.println("Deposit successful.");
        System.out.println("Amount: ₹" + amount);
        System.out.println("Current Balance: ₹" +
                currentAccount.getBalance());
    }

    private void transfer() {

        System.out.print("Enter recipient Account ID: ");
        String recipientId = scanner.nextLine();

        Account recipient = bank.getAccount(recipientId);

        if (recipient == null) {
            System.out.println("Recipient account not found.");
            return;
        }

        if (recipient == currentAccount) {
            System.out.println("You cannot transfer money to yourself.");
            return;
        }

        System.out.print("Enter transfer amount: ");

        double amount;

        try {
            amount = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount.");
            return;
        }

        if (amount <= 0) {
            System.out.println("Amount must be greater than zero.");
            return;
        }

        // Balance check
        if (amount > currentAccount.getBalance()) {
            System.out.println("Insufficient Funds");
            return;
        }

        // Deduct from sender
        currentAccount.withdraw(amount);

        // Add to recipient
        recipient.deposit(amount);

        // Sender transaction
        currentAccount.addTransaction(
                new Transaction(
                        "TRANSFER",
                        amount,
                        "Transferred to Account " + recipientId
                )
        );

        // Recipient transaction
        recipient.addTransaction(
                new Transaction(
                        "TRANSFER RECEIVED",
                        amount,
                        "Received from Account " +
                        currentAccount.getUserId()
                )
        );

        System.out.println("Transfer successful.");
        System.out.println("Transferred ₹" + amount +
                " to Account " + recipientId);

        System.out.println("Remaining Balance: ₹" +
                currentAccount.getBalance());
    }
}