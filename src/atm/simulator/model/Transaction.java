package atm.simulator.model;

import java.util.Date;

public class Transaction {
    private String cardNumber;
    private String type; // Deposit, Withdrawal, etc.
    private double amount;
    private Date date;

    public Transaction(String cardNumber, String type, double amount, Date date) {
        this.cardNumber = cardNumber;
        this.type = type;
        this.amount = amount;
        this.date = date;
    }

    // Getters
    public String getCardNumber() {
        return cardNumber;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }
}
