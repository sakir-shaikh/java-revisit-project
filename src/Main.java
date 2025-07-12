import config.DatabaseConfig;
import java.sql.Connection;
import java.sql.SQLException;
import ui.MainMenu;
import util.AppMessages;
import util.ConsoleColors;

public class Main {
    
    public static void main(String[] args) {
        Connection connection = null;
        
        try {
            ConsoleColors.printTitle(AppMessages.APP_TITLE);
            ConsoleColors.printInfo(AppMessages.WELCOME);
            
            // Test database connection
            if (DatabaseConfig.testConnection()) {
                ConsoleColors.printSuccess("Database connected successfully!");
                ConsoleColors.printInfo("Database: " + DatabaseConfig.getDatabaseInfo());
                connection = DatabaseConfig.getConnection();
            } else {
                ConsoleColors.printWarning("Database connection failed. Using JSON database.");
            }
            
            // Start the application
            MainMenu mainMenu = new MainMenu();
            mainMenu.start();
            
        } catch (SQLException e) {
            ConsoleColors.printError("Database connection failed: " + e.getMessage());
            ConsoleColors.printInfo("Starting application with JSON database...");
            
            // Continue with JSON database if MySQL fails
            try {
                MainMenu mainMenu = new MainMenu();
                mainMenu.start();
            } catch (Exception ex) {
                ConsoleColors.printError(AppMessages.UNEXPECTED_ERROR + ex.getMessage());
                ex.printStackTrace();
            }
        } catch (Exception e) {
            ConsoleColors.printError(AppMessages.UNEXPECTED_ERROR + e.getMessage());
            e.printStackTrace();
        } finally {
            // Close database connection
            if (connection != null) {
                try {
                    connection.close();
                    ConsoleColors.printInfo("Database connection closed.");
                } catch (SQLException e) {
                    ConsoleColors.printError("Error closing database connection: " + e.getMessage());
                }
            }
        }
    }
}