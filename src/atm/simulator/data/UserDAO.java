package atm.simulator.data;

import atm.simulator.domain.repository.IUserDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Data Access Object for handling User-related database operations.
 */
public class UserDAO implements IUserDAO {
    private DatabaseConnection con;

    /**
     * Constructs a UserDAO with a database connection.
     * 
     * @param con the database connection to use
     */
    public UserDAO(DatabaseConnection con) {
        this.con = con;
    }

    /**
     * Authenticates a user based on card number and PIN.
     * 
     * @param cardNumber the card number to check
     * @param pin        the PIN to check
     * @return true if authentication succeeds, false otherwise
     */
    public boolean authenticate(String cardNumber, String pin) {
        String query = "SELECT * FROM Account1 WHERE Aid = ? AND Pin_number = ?";
        try (PreparedStatement pstmt = con.connection.prepareStatement(query)) {
            pstmt.setString(1, cardNumber);
            pstmt.setString(2, pin);
            try (ResultSet resultSet = pstmt.executeQuery()) {
                return resultSet.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Updates the PIN for a specified card number.
     * 
     * @param cardNumber the card number to update PIN for
     * @param newPin     the new PIN to set
     * @return true if update succeeds, false otherwise
     */
    public boolean updatePin(String cardNumber, String newPin) {
        String query = "UPDATE Account1 SET Pin_number = ? WHERE Aid = ?";
        try (PreparedStatement pstmt = con.connection.prepareStatement(query)) {
            pstmt.setString(1, newPin);
            pstmt.setString(2, cardNumber);
            pstmt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
