package atm.simulator.data;

import atm.simulator.domain.repository.IUserDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO implements IUserDAO {
    private DatabaseConnection con;

    public UserDAO() {
        con = new DatabaseConnection();
    }

    public boolean authenticate(String cardNumber, String pin) {
        try {
            String query = "SELECT * FROM Account1 WHERE Aid = ? AND Pin_number = ?";
            PreparedStatement pstmt = con.connection.prepareStatement(query);
            pstmt.setString(1, cardNumber);
            pstmt.setString(2, pin);
            ResultSet resultSet = pstmt.executeQuery();
            return resultSet.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updatePin(String cardNumber, String newPin) {
        try {
            String query = "UPDATE Account1 SET Pin_number = ? WHERE Aid = ?";
            PreparedStatement pstmt = con.connection.prepareStatement(query);
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
