package atm.simulator.domain.repository;

/**
 * Interface for User database operations.
 */
public interface IUserDAO {
    /**
     * Authenticates a user.
     * 
     * @param cardNumber the card number
     * @param pin        the PIN
     * @return true if authenticated, false otherwise
     */
    boolean authenticate(String cardNumber, String pin);

    /**
     * Updates account PIN.
     * 
     * @param cardNumber the card number
     * @param newPin     the new PIN
     * @return true if updated, false otherwise
     */
    boolean updatePin(String cardNumber, String newPin);
}
