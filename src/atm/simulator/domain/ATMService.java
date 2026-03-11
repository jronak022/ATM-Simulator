package atm.simulator.domain;

import atm.simulator.domain.repository.ITransactionDAO;
import atm.simulator.domain.repository.IUserDAO;
import atm.simulator.model.Transaction;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class that provides the core business logic for the ATM.
 * Orchestrates operations between DAOs and UI.
 */
public class ATMService {
    private final IUserDAO userDAO;
    private final ITransactionDAO transactionDAO;

    /**
     * Constructs an ATMService with the necessary DAOs.
     * 
     * @param userDAO        the user data access object
     * @param transactionDAO the transaction data access object
     */
    public ATMService(IUserDAO userDAO, ITransactionDAO transactionDAO) {
        this.userDAO = userDAO;
        this.transactionDAO = transactionDAO;
    }

    /**
     * Authenticates a user.
     * 
     * @param cardNumber the card number
     * @param pin        the PIN
     * @return true if successful, false otherwise
     */
    public boolean login(String cardNumber, String pin) {
        return userDAO.authenticate(cardNumber, pin);
    }

    /**
     * Deposits money into an account.
     * 
     * @param cardNumber the card number
     * @param amount     the amount to deposit
     */
    public void deposit(String cardNumber, double amount) {
        transactionDAO.addTransaction(cardNumber, "Deposit", amount);
    }

    /**
     * Enumeration of possible transaction results.
     */
    public enum TransactionResult {
        SUCCESS, INSUFFICIENT_BALANCE, INVALID_AMOUNT, EXCEEDS_LIMIT
    }

    /**
     * Withdraws money from an account.
     * 
     * @param cardNumber the card number
     * @param amount     the amount to withdraw
     * @return the result of the withdrawal operation
     */
    public TransactionResult withdraw(String cardNumber, double amount) {
        if (amount <= 0 || amount % 100 != 0) {
            return TransactionResult.INVALID_AMOUNT;
        }
        if (amount > 10000) {
            return TransactionResult.EXCEEDS_LIMIT;
        }

        double currentBalance = transactionDAO.getBalance(cardNumber);
        if (currentBalance >= amount) {
            transactionDAO.addTransaction(cardNumber, "Withdrawal", amount);
            return TransactionResult.SUCCESS;
        }
        return TransactionResult.INSUFFICIENT_BALANCE;
    }

    /**
     * Retrieves the current balance for an account.
     * 
     * @param cardNumber the card number
     * @return the current balance
     */
    public double getBalance(String cardNumber) {
        return transactionDAO.getBalance(cardNumber);
    }

    /**
     * Retrieves the last 10 transactions for an account.
     * 
     * @param cardNumber the card number
     * @return a list of transactions
     */
    public List<Transaction> getMiniStatement(String cardNumber) {
        return transactionDAO.getTransactions(cardNumber).stream().limit(10).collect(Collectors.toList());
    }

    /**
     * Changes the PIN for an account.
     * 
     * @param cardNumber the card number
     * @param newPin     the new PIN
     * @return true if successful, false otherwise
     */
    public boolean changePin(String cardNumber, String newPin) {
        return userDAO.updatePin(cardNumber, newPin);
    }
}
