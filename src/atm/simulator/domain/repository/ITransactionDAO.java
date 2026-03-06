package atm.simulator.domain.repository;

import atm.simulator.model.Transaction;
import java.util.List;

public interface ITransactionDAO {
    void addTransaction(String cardNumber, String type, double amount);

    double getBalance(String cardNumber);

    List<Transaction> getTransactions(String cardNumber);
}
