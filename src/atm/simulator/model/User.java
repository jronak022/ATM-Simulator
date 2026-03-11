package atm.simulator.model;

/**
 * Represents a user of the ATM system.
 * Contains user credentials and profile information.
 */
public class User {
    private String cardNumber;
    private String pin;
    private String name;

    /**
     * Constructs a new User with the specified card number and PIN.
     * 
     * @param cardNumber the unique card number for the user
     * @param pin        the personal identification number for the user
     */
    public User(String cardNumber, String pin) {
        this.cardNumber = cardNumber;
        this.pin = pin;
    }

    /**
     * Gets the user's card number.
     * 
     * @return the card number
     */
    public String getCardNumber() {
        return cardNumber;
    }

    /**
     * Sets the user's card number.
     * 
     * @param cardNumber the card number to set
     */
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    /**
     * Gets the user's PIN.
     * 
     * @return the PIN
     */
    public String getPin() {
        return pin;
    }

    /**
     * Sets the user's PIN.
     * 
     * @param pin the PIN to set
     */
    public void setPin(String pin) {
        this.pin = pin;
    }

    /**
     * Gets the user's full name.
     * 
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the user's full name.
     * 
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
}
