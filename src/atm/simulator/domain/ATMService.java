package atm.simulator.domain;

import atm.simulator.domain.repository.ITransactionDAO;
import atm.simulator.domain.repository.IUserDAO;
import atm.simulator.model.Transaction;
import java.util.List;
import java.util.stream.Collectors;

public class ATMService {
    private final IUserDAO userDAO;
    private final ITransactionDAO transactionDAO;

    public ATMService(IUserDAO userDAO, ITransactionDAO transactionDAO) {
        this.userDAO = userDAO;
        this.transactionDAO = transactionDAO;
    }

    public boolean login(String cardNumber, String pin) {
        return userDAO.authenticate(cardNumber, pin);
    }

    public void deposit(String cardNumber, double amount) {
        transactionDAO.addTransaction(cardNumber, "Deposit", amount);
    }

    public enum TransactionResult {
        SUCCESS, INSUFFICIENT_BALANCE, INVALID_AMOUNT, EXCEEDS_LIMIT
    }

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

    public double getBalance(String cardNumber) {
        return transactionDAO.getBalance(cardNumber);
    }

    public List<Transaction> getMiniStatement(String cardNumber) {
        return transactionDAO.getTransactions(cardNumber).stream().limit(10).collect(Collectors.toList());
    }

    public boolean changePin(String cardNumber, String newPin) {
        return userDAO.updatePin(cardNumber, newPin);
    }
}
