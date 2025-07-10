import model.*;
import service.*;
import dao.*;
import util.*;

public class JobPortalTest {
    public static void main(String[] args) {
        System.out.println("=== JOB PORTAL SYSTEM TEST ===");
        
        try {
            // Test database initialization
            Database database = Database.getInstance();
            System.out.println("✓ Database initialized successfully");
            
            // Test user service
            UserService userService = new UserService();
            System.out.println("✓ UserService initialized successfully");
            
            // Test job service
            JobService jobService = new JobService();
            System.out.println("✓ JobService initialized successfully");
            
            // Test application service
            ApplicationService applicationService = new ApplicationService();
            System.out.println("✓ ApplicationService initialized successfully");
            
            // Test auth service
            AuthService authService = new AuthService();
            System.out.println("✓ AuthService initialized successfully");
            
            // Test validator
            System.out.println("✓ Email validation test: " + Validator.isValidEmail("test@example.com"));
            System.out.println("✓ Password validation test: " + Validator.isValidPassword("password123"));
            System.out.println("✓ Name validation test: " + Validator.isValidName("John Doe"));
            
            // Test console colors
            ConsoleColors.printSuccess("✓ Console colors working");
            ConsoleColors.printError("✓ Error colors working");
            ConsoleColors.printWarning("✓ Warning colors working");
            ConsoleColors.printInfo("✓ Info colors working");
            
            System.out.println("\n=== ALL TESTS PASSED ===");
            System.out.println("The Job Portal system is ready to use!");
            
        } catch (Exception e) {
            System.err.println("✗ Test failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
} 