package atm.simulator.domain.repository;

import atm.simulator.model.Transaction;
import java.util.List;

/**
 * Interface for Transaction database operations.
 */
public interface ITransactionDAO {
    /**
     * Adds a transaction entry.
     * 
     * @param cardNumber the card number
     * @param type       the transaction type
     * @param amount     the transaction amount
     */
    void addTransaction(String cardNumber, String type, double amount);

    /**
     * Gets account balance.
     * 
     * @param cardNumber the card number
     * @return current balance
     */
    double getBalance(String cardNumber);

    /**
     * Retrieves transactions for an account.
     * 
     * @param cardNumber the card number
     * @return list of transactions
     */
    List<Transaction> getTransactions(String cardNumber);
}
