# ATM Simulator

A comprehensive ATM Simulator application built with Java Swing and MySQL, featuring a layered architecture (UI, Domain, and Data).

## Features
- **Secure Login**: Authentication via card number and PIN.
- **Deposit**: Deposit funds into the account (multiples of 100).
- **Withdrawal**: Withdraw funds with balance and limit checks.
- **Fast Cash**: Quick withdrawal options (Rs 100 to Rs 10,000).
- **Mini Statement**: View the last 10 transactions.
- **Balance Enquiry**: Real-time balance checking.
- **PIN Change**: Securely update account PIN.

## Project Architecture
The project follows a clean, layered architecture to ensure maintainability and scalability:

- **`ui` Package**: Contains Swing-based frames for user interaction.
- **`domain` Package**: Implements business logic (`ATMService`) and defines repository interfaces.
- **`data` Package**: Handles database connectivity and implementations of DAO interfaces using JDBC.
- **`model` Package**: Defines core entities like `User` and `Transaction`.
- **`AppContext`**: A singleton class that provides centralized access to application-wide services and DAOs.

## Tech Stack
- **Language**: Java
- **Database**: MySQL
- **UI Framework**: Java Swing / AWT
- **Build Tool**: IDE-based (IntelliJ/Eclipse)

## Prerequisites
1. **Java JDK 8 or higher**
2. **MySQL Server**
3. **MySQL Connector/J** (ensure it's in your project dependencies)

## Setup Instructions

### 1. Database Setup
Create a database named `banksystem` in MySQL and create the necessary tables:

```sql
CREATE DATABASE banksystem;
USE banksystem;

-- Table for user accounts
CREATE TABLE Account1 (
    Aid VARCHAR(20) PRIMARY KEY,
    Pin_number VARCHAR(10),
    Name VARCHAR(50)
);

-- Table for transactions
CREATE TABLE Transaction (
    Tid VARCHAR(50) PRIMARY KEY,
    Aid VARCHAR(20),
    DOT DATE,
    Withdrawal_amt DOUBLE,
    Deposit_amt DOUBLE,
    Current_bal VARCHAR(20),
    FOREIGN KEY (Aid) REFERENCES Account1(Aid)
);
```

### 2. Configuration
Update `config.properties` in the project root with your MySQL credentials:
```properties
db.url=jdbc:mysql://localhost:3306/banksystem
db.user=your_username
db.password=your_password
```

### 3. Running the Application
Run the `MainApp.java` file located in `src/atm/simulator/` to start the application.

## Contributing
Contributions are welcome! If you'd like to improve the ATM Simulator, please follow these steps:

1. **Fork** the repository.
2. **Create a branch** for your feature or bug fix (`git checkout -b feature/your-feature-name`).
3. **Commit** your changes with clear messages.
4. **Push** to your branch.
5. **Open a Pull Request** describing your changes.

Please ensure your code follows the existing style and includes proper Javadoc documentation.

## License
This project is licensed under the [MIT License](LICENSE).

```text
MIT License

Copyright (c) 2026

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```

## Contact
Project Link: [https://github.com/jronak2003/ATM-Simulator](https://github.com/jronak2003/ATM-Simulator)
