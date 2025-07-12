package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Database configuration and connection management
 */
public class DatabaseConfig {
    
    // Default values if properties file is not found
    private static final String DEFAULT_DB_URL = "jdbc:mysql://localhost:3306/Jobportal";
    private static final String DEFAULT_DB_USER = "root";
    private static final String DEFAULT_DB_PASSWORD = "Sakir@123#321";
    private static final String DEFAULT_DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    
    private static String dbUrl;
    private static String dbUser;
    private static String dbPassword;
    private static String dbDriver;
    
    static {
        loadDatabaseProperties();
    }
    
    /**
     * Load database configuration from properties file
     */
    private static void loadDatabaseProperties() {
        Properties props = new Properties();
        
        try (FileInputStream fis = new FileInputStream("config/database.properties")) {
            props.load(fis);
            dbUrl = props.getProperty("db.url", DEFAULT_DB_URL);
            dbUser = props.getProperty("db.user", DEFAULT_DB_USER);
            dbPassword = props.getProperty("db.password", DEFAULT_DB_PASSWORD);
            dbDriver = props.getProperty("db.driver", DEFAULT_DB_DRIVER);
        } catch (IOException e) {
            // Use default values if properties file is not found
            dbUrl = DEFAULT_DB_URL;
            dbUser = DEFAULT_DB_USER;
            dbPassword = DEFAULT_DB_PASSWORD;
            dbDriver = DEFAULT_DB_DRIVER;
        }
    }
    
    /**
     * Get a database connection
     */
    public static Connection getConnection() throws SQLException {
        try {
            // Load the database driver
            Class.forName(dbDriver);
            
            // Create and return connection
            return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Database driver not found: " + dbDriver, e);
        }
    }
    
    /**
     * Test database connection
     */
    public static boolean testConnection() {
        try (Connection connection = getConnection()) {
            return connection.isValid(5);
        } catch (SQLException e) {
            return false;
        }
    }
    
    /**
     * Get database information
     */
    public static String getDatabaseInfo() {
        try (Connection connection = getConnection()) {
            return connection.getMetaData().getDatabaseProductName() + 
                   " " + connection.getMetaData().getDatabaseProductVersion();
        } catch (SQLException e) {
            return "Database info unavailable: " + e.getMessage();
        }
    }
    
    // Getters for configuration
    public static String getDbUrl() { return dbUrl; }
    public static String getDbUser() { return dbUser; }
    public static String getDbDriver() { return dbDriver; }
} 