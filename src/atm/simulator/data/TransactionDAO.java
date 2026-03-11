package atm.simulator.data;

import atm.simulator.domain.repository.ITransactionDAO;

import atm.simulator.model.Transaction;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object for handling Transaction-related database operations.
 */
public class TransactionDAO implements ITransactionDAO {
    private DatabaseConnection con;

    /**
     * Constructs a TransactionDAO with a database connection.
     * 
     * @param con the database connection to use
     */
    public TransactionDAO(DatabaseConnection con) {
        this.con = con;
    }

    /**
     * Records a new transaction in the database.
     * 
     * @param cardNumber the card number for the transaction
     * @param type       the type of transaction (Deposit/Withdrawal)
     * @param amount     the amount of the transaction
     */
    public void addTransaction(String cardNumber, String type, double amount) {
        String tid = "T" + System.currentTimeMillis() + (int) (Math.random() * 1000);
        java.sql.Date sqlDate = new java.sql.Date(System.currentTimeMillis());
        double withdrawalAmt = type.equals("Withdrawal") ? amount : 0;
        double depositAmt = type.equals("Deposit") ? amount : 0;

        String query = "INSERT INTO Transaction (Tid, Aid, DOT, Withdrawal_amt, Deposit_amt, Current_bal) VALUES(?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = con.connection.prepareStatement(query)) {
            pstmt.setString(1, tid);
            pstmt.setString(2, cardNumber);
            pstmt.setDate(3, sqlDate);
            pstmt.setDouble(4, withdrawalAmt);
            pstmt.setDouble(5, depositAmt);
            pstmt.setString(6, "");
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves a list of transactions for a specific card number.
     * 
     * @param cardNumber the card number to fetch transactions for
     * @return a list of Transaction objects
     */
    public List<Transaction> getTransactions(String cardNumber) {
        List<Transaction> list = new ArrayList<>();
        String query = "SELECT * FROM Transaction WHERE Aid = ? ORDER BY DOT DESC";
        try (PreparedStatement pstmt = con.connection.prepareStatement(query)) {
            pstmt.setString(1, cardNumber);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    String withdrawalStr = rs.getString("Withdrawal_amt");
                    boolean isWithdrawal = Double.parseDouble(withdrawalStr) > 0;
                    list.add(new Transaction(
                            rs.getString("Aid"),
                            isWithdrawal ? "Withdrawal" : "Deposit",
                            isWithdrawal ? rs.getDouble("Withdrawal_amt") : rs.getDouble("Deposit_amt"),
                            rs.getDate("DOT")));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Calculates the current balance for a specified card number.
     * 
     * @param cardNumber the card number to calculate balance for
     * @return the current balance
     */
    public double getBalance(String cardNumber) {
        double balance = 0;
        String query = "SELECT SUM(Deposit_amt) - SUM(Withdrawal_amt) AS Current_bal FROM Transaction WHERE Aid = ?";
        try (PreparedStatement pstmt = con.connection.prepareStatement(query)) {
            pstmt.setString(1, cardNumber);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    balance = rs.getDouble("Current_bal");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return balance;
    }
}
