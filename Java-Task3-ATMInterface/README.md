# 🏧 ATM Interface — Java OOP

A **console-based ATM simulation** built using **Java** and **Object-Oriented Programming (OOP)** concepts. The application allows users to securely log in using a User ID and PIN and perform common banking operations such as withdrawal, deposit, transfer, and transaction history.

## 📌 Project Overview

This project is designed to demonstrate practical implementation of Java OOP concepts through a real-world ATM banking scenario.

Users can:

- 🔐 Authenticate using User ID and PIN
- 🚫 Get denied access after 3 incorrect login attempts
- 💰 Withdraw money
- 💵 Deposit money
- 🔄 Transfer money to another account
- 📜 View transaction history
- 🚪 Safely exit the ATM

---

## 🛠️ Tech Stack

- **Language:** Java
- **Application Type:** Console Application
- **Concepts:** Object-Oriented Programming
- **Data Structures:** `ArrayList`, `HashMap`
- **Java Features:** Classes, Objects, Encapsulation, Constructors, Methods, Switch-Case, Exception Handling

---

## 📂 Project Structure

```text
ATMProject/
│
├── Main.java
├── ATM.java
├── Account.java
├── Transaction.java
├── Bank.java
└── README.md
```

### Class Responsibilities

| Class         | Responsibility                                   |
| ------------- | ------------------------------------------------ |
| `Main`        | Entry point of the application                   |
| `ATM`         | Handles login, menu and banking operations       |
| `Account`     | Stores account details, balance and transactions |
| `Transaction` | Represents individual transactions               |
| `Bank`        | Manages multiple user accounts                   |

---

## ✨ Features

### 🔐 User Authentication

The ATM asks the user to enter:

- User ID
- PIN

Users get a maximum of **3 attempts** to enter the correct credentials.

After three failed attempts:

```text
Too many incorrect attempts.
Account access denied.
```

---

### 💰 Withdraw

Users can withdraw money from their account.

Before processing the withdrawal, the application checks whether the account has sufficient funds.

If the balance is insufficient:

```text
Insufficient Funds
```

Otherwise, the balance is updated and the transaction is added to the transaction history.

---

### 💵 Deposit

Users can deposit money into their account.

The deposited amount is added to the current balance and the transaction is recorded.

---

### 🔄 Transfer

Users can transfer money to another account using the recipient's Account ID.

The system:

1. Validates the recipient account.
2. Checks the sender's balance.
3. Deducts the amount from the sender.
4. Adds the amount to the recipient.
5. Records the transaction for both accounts.

---

### 📜 Transaction History

All transactions are stored using an `ArrayList<Transaction>`.

The transaction history displays:

- Date and time
- Transaction type
- Amount
- Transaction details

Example:

```text
2026-09-08T00:10:15 | DEPOSIT | Amount: ₹2000.0 | Cash deposited
2026-09-08T00:11:32 | WITHDRAW | Amount: ₹500.0 | Cash withdrawn
2026-09-08T00:12:45 | TRANSFER | Amount: ₹1000.0 | Transferred to Account 1002
```

---

## 👤 Sample Accounts

The application contains sample accounts for testing.

| User ID | PIN    | Initial Balance |
| ------- | ------ | --------------: |
| `1001`  | `1234` |         ₹10,000 |
| `1002`  | `5678` |          ₹5,000 |
| `1003`  | `1111` |         ₹15,000 |

### Example Login

```text
Enter User ID: 1001
Enter PIN: 1234

Login successful!
Welcome, User 1001
```

---

## 🖥️ Main Menu

After successful authentication, the following menu is displayed:

```text
================================
          ATM MENU
================================
1. Transaction History
2. Withdraw
3. Deposit
4. Transfer
5. Quit
================================
Enter your choice:
```

---

## 🧠 OOP Concepts Used

### 1. Encapsulation

Account details are kept private and accessed through methods.

```java
private String userId;
private String pin;
private double balance;
```

Getter methods are used to access required information.

---

### 2. Classes and Objects

The project uses multiple classes to separate different responsibilities:

```java
Bank bank = new Bank();
ATM atm = new ATM(bank);
```

---

### 3. Constructors

Constructors initialize objects with their required values.

```java
public Account(String userId, String pin, double balance) {
    this.userId = userId;
    this.pin = pin;
    this.balance = balance;
}
```

---

### 4. ArrayList

Transactions are stored dynamically using:

```java
ArrayList<Transaction>
```

---

### 5. HashMap

The `Bank` class uses a `HashMap` to efficiently find accounts using their User ID.

```java
HashMap<String, Account> accounts;
```

---

### 6. Switch-Case

The ATM menu is implemented using a switch statement:

```java
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
        return;
}
```

---

## ⚙️ How to Run

### Prerequisites

Make sure **Java JDK** is installed on your system.

Check your Java installation:

```bash
java -version
```

Check the Java compiler:

```bash
javac -version
```

### Step 1 — Clone the Repository

```bash
git clone <your-repository-url>
```

### Step 2 — Open the Project Folder

```bash
cd ATMProject
```

### Step 3 — Compile the Program

```bash
javac *.java
```

### Step 4 — Run the Application

```bash
java Main
```

---

## 🧪 Example Workflow

```text
================================
        WELCOME TO ATM
================================

Enter User ID: 1001
Enter PIN: 1234

Login successful!
Welcome, User 1001

================================
          ATM MENU
================================
1. Transaction History
2. Withdraw
3. Deposit
4. Transfer
5. Quit
================================

Enter your choice: 2

Enter withdrawal amount: 2000

Withdrawal successful.
Amount: ₹2000.0
Remaining Balance: ₹8000.0
```

---

## 🚨 Error Handling

The application handles common invalid inputs such as:

- Incorrect User ID
- Incorrect PIN
- More than 3 login attempts
- Invalid menu choices
- Invalid numerical input
- Negative or zero transaction amounts
- Insufficient balance
- Invalid recipient account
- Transfer to the same account

---

## 🔮 Future Improvements

Possible improvements for future versions:

- [ ] Add database connectivity
- [ ] Persist transaction history after program exit
- [ ] Add account creation
- [ ] Add PIN change functionality
- [ ] Add balance inquiry option
- [ ] Add account deletion
- [ ] Add admin functionality
- [ ] Add GUI using Java Swing or JavaFX
- [ ] Add encryption/security for PINs
- [ ] Add receipt generation
- [ ] Add JDBC/MySQL integration

---

## 🎯 Learning Objectives

This project helps demonstrate:

- Java fundamentals
- Object-Oriented Programming
- Encapsulation
- Class and object design
- Collections Framework
- `ArrayList`
- `HashMap`
- Exception handling
- User input handling
- Menu-driven applications
- Real-world software design

---

## 📄 License

This project is created for **educational and learning purposes**.

---

## 👨‍💻 Author

**Aman Raj**

Built as part of a Java OOP coding challenge.

⭐ If you found this project useful, consider giving the repository a star!
