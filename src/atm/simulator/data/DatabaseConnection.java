package atm.simulator.data;

import java.sql.*;

/**
 * Handles the connection to the MySQL database.
 * Loads configuration from config.properties.
 */
public class DatabaseConnection {
    /** The active SQL connection. */
    public Connection connection;
    /** The SQL statement object for executing queries. */
    public Statement statement;

    /**
     * Initializes the database connection using properties from config.properties.
     */
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
