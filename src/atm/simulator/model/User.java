package atm.simulator.model;

public class User {
    private String cardNumber;
    private String pin;
    private String name;

    public User(String cardNumber, String pin) {
        this.cardNumber = cardNumber;
        this.pin = pin;
    }

    // Getters and Setters
    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
