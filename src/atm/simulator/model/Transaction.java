package atm.simulator.model;

import java.util.Date;

/**
 * Represents a financial transaction in the ATM system.
 */
public class Transaction {
    private String cardNumber;
    private String type; // Deposit, Withdrawal, etc.
    private double amount;
    private Date date;

    /**
     * Constructs a new Transaction.
     * 
     * @param cardNumber the card number associated with the transaction
     * @param type       the type of transaction (e.g., "Deposit", "Withdrawal")
     * @param amount     the transaction amount
     * @param date       the date and time of the transaction
     */
    public Transaction(String cardNumber, String type, double amount, Date date) {
        this.cardNumber = cardNumber;
        this.type = type;
        this.amount = amount;
        this.date = date;
    }

    /**
     * Gets the card number.
     * 
     * @return the card number
     */
    public String getCardNumber() {
        return cardNumber;
    }

    /**
     * Gets the transaction type.
     * 
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Gets the transaction amount.
     * 
     * @return the amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Gets the transaction date.
     * 
     * @return the date
     */
    public Date getDate() {
        return date;
    }
}
