package atm.simulator.data;

import atm.simulator.domain.repository.IUserDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO implements IUserDAO {
    private DatabaseConnection con;

    public UserDAO(DatabaseConnection con) {
        this.con = con;
    }

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
