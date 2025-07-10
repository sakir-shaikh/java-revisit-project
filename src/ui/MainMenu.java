package ui;

import service.AuthService;
import model.User;
import model.JobSeeker;
import model.Recruiter;
import util.ConsoleColors;

import java.util.Scanner;

public class MainMenu {
    private AuthService authService;
    private Scanner scanner;
    private User currentUser;

    public MainMenu() {
        this.authService = new AuthService();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        ConsoleColors.printTitle("JOB PORTAL SYSTEM");
        
        while (true) {
            showMainMenu();
            int choice = getIntInput("Enter your choice: ");
            
            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    register();
                    break;
                case 3:
                    ConsoleColors.printInfo("Thank you for using Job Portal System!");
                    return;
                default:
                    ConsoleColors.printError("Invalid choice. Please try again.");
            }
        }
    }

    private void showMainMenu() {
        System.out.println("\n" + ConsoleColors.CYAN + "=== MAIN MENU ===" + ConsoleColors.RESET);
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Exit");
        System.out.println();
    }

    private void login() {
        ConsoleColors.printTitle("LOGIN");
        
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        
        try {
            currentUser = authService.authenticate(email, password);
            ConsoleColors.printSuccess("Login successful! Welcome, " + currentUser.getName() + "!");
            
            if (authService.isJobSeeker(currentUser)) {
                JobSeekerMenu jobSeekerMenu = new JobSeekerMenu((JobSeeker) currentUser);
                jobSeekerMenu.start();
            } else if (authService.isRecruiter(currentUser)) {
                RecruiterMenu recruiterMenu = new RecruiterMenu((Recruiter) currentUser);
                recruiterMenu.start();
            }
            
            currentUser = null; // Logout after menu closes
        } catch (IllegalArgumentException e) {
            ConsoleColors.printError("Login failed: " + e.getMessage());
        }
    }

    private void register() {
        ConsoleColors.printTitle("REGISTRATION");
        
        System.out.println("1. Register as Job Seeker");
        System.out.println("2. Register as Recruiter");
        System.out.println("3. Back to Main Menu");
        
        int choice = getIntInput("Enter your choice: ");
        
        switch (choice) {
            case 1:
                registerJobSeeker();
                break;
            case 2:
                registerRecruiter();
                break;
            case 3:
                return;
            default:
                ConsoleColors.printError("Invalid choice.");
        }
    }

    private void registerJobSeeker() {
        ConsoleColors.printTitle("JOB SEEKER REGISTRATION");
        
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        
        try {
            JobSeeker jobSeeker = authService.registerJobSeeker(name, email, password);
            ConsoleColors.printSuccess("Registration successful! Welcome, " + jobSeeker.getName() + "!");
        } catch (IllegalArgumentException e) {
            ConsoleColors.printError("Registration failed: " + e.getMessage());
        }
    }

    private void registerRecruiter() {
        ConsoleColors.printTitle("RECRUITER REGISTRATION");
        
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        
        try {
            Recruiter recruiter = authService.registerRecruiter(name, email, password);
            ConsoleColors.printSuccess("Registration successful! Welcome, " + recruiter.getName() + "!");
        } catch (IllegalArgumentException e) {
            ConsoleColors.printError("Registration failed: " + e.getMessage());
        }
    }

    private int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                ConsoleColors.printError("Please enter a valid number.");
            }
        }
    }
} 