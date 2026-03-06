package atm.simulator.data;

import java.sql.*;

public class DatabaseConnection {
    public Connection connection;
    public Statement statement;

    public DatabaseConnection() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/banksystem", "root", "jain#2003");
            statement = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
