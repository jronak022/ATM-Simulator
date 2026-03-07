package atm.simulator.data;

import java.sql.*;

public class DatabaseConnection {
    public Connection connection;
    public Statement statement;

    public DatabaseConnection() {
        try {
            java.util.Properties props = new java.util.Properties();
            try (java.io.FileInputStream fis = new java.io.FileInputStream("config.properties")) {
                props.load(fis);
            }
            String url = props.getProperty("db.url");
            String user = props.getProperty("db.user");
            String pass = props.getProperty("db.password");

            connection = DriverManager.getConnection(url, user, pass);
            statement = connection.createStatement();
        } catch (Exception e) {
            System.err.println("Error: Could not load config.properties or connect to database.");
            e.printStackTrace();
        }
    }
}
