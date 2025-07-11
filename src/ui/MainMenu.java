package ui;

import java.util.Scanner;
import model.JobSeeker;
import model.Recruiter;
import model.User;
import service.AuthService;
import util.AppMessages;
import util.ConsoleColors;

public class MainMenu {
    private AuthService authService;
    private Scanner scanner;
    private User currentUser;

    public MainMenu() {
        this.authService = new AuthService();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        ConsoleColors.printTitle(AppMessages.APP_TITLE);
        
        while (true) {
            showMainMenu();
            int choice = getIntInput(AppMessages.ENTER_CHOICE);
            
            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    register();
                    break;
                case 3:
                    ConsoleColors.printInfo(AppMessages.EXIT);
                    return;
                default:
                    ConsoleColors.printError(AppMessages.INVALID_CHOICE);
            }
        }
    }

    private void showMainMenu() {
        System.out.println("\n" + ConsoleColors.CYAN + AppMessages.MAIN_MENU + ConsoleColors.RESET);
        System.out.println("1. " + AppMessages.LOGIN);
        System.out.println("2. " + AppMessages.REGISTER);
        System.out.println("3. " + AppMessages.EXIT_OPTION);
        System.out.println();
    }

    private void login() {
        ConsoleColors.printTitle(AppMessages.LOGIN);
        
        System.out.print(AppMessages.ENTER_EMAIL);
        String email = scanner.nextLine();
        
        System.out.print(AppMessages.ENTER_PASSWORD);
        String password = scanner.nextLine();
        
        try {
            currentUser = authService.authenticate(email, password);
            ConsoleColors.printSuccess(AppMessages.LOGIN_SUCCESS + currentUser.getName() + "!");
            
            if (authService.isJobSeeker(currentUser)) {
                JobSeekerMenu jobSeekerMenu = new JobSeekerMenu((JobSeeker) currentUser);
                jobSeekerMenu.start();
            } else if (authService.isRecruiter(currentUser)) {
                RecruiterMenu recruiterMenu = new RecruiterMenu((Recruiter) currentUser);
                recruiterMenu.start();
            }
            
            currentUser = null; // Logout after menu closes
        } catch (IllegalArgumentException e) {
            ConsoleColors.printError(AppMessages.LOGIN_FAILED + e.getMessage());
        }
    }

    private void register() {
        ConsoleColors.printTitle("REGISTRATION");
        
        System.out.println("1. Register as Job Seeker");
        System.out.println("2. Register as Recruiter");
        System.out.println("3. Back to Main Menu");
        
        int choice = getIntInput(AppMessages.ENTER_CHOICE);
        
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
                ConsoleColors.printError(AppMessages.INVALID_CHOICE);
        }
    }

    private void registerJobSeeker() {
        ConsoleColors.printTitle("JOB SEEKER REGISTRATION");
        
        System.out.print(AppMessages.ENTER_NAME);
        String name = scanner.nextLine();
        
        System.out.print(AppMessages.ENTER_EMAIL);
        String email = scanner.nextLine();
        
        System.out.print(AppMessages.ENTER_PASSWORD);
        String password = scanner.nextLine();
        
        try {
            JobSeeker jobSeeker = authService.registerJobSeeker(name, email, password);
            ConsoleColors.printSuccess(AppMessages.REGISTRATION_SUCCESS + jobSeeker.getName() + "!");
        } catch (IllegalArgumentException e) {
            ConsoleColors.printError(AppMessages.REGISTRATION_FAILED + e.getMessage());
        }
    }

    private void registerRecruiter() {
        ConsoleColors.printTitle("RECRUITER REGISTRATION");
        
        System.out.print(AppMessages.ENTER_NAME);
        String name = scanner.nextLine();
        
        System.out.print(AppMessages.ENTER_EMAIL);
        String email = scanner.nextLine();
        
        System.out.print(AppMessages.ENTER_PASSWORD);
        String password = scanner.nextLine();
        
        try {
            Recruiter recruiter = authService.registerRecruiter(name, email, password);
            ConsoleColors.printSuccess(AppMessages.REGISTRATION_SUCCESS + recruiter.getName() + "!");
        } catch (IllegalArgumentException e) {
            ConsoleColors.printError(AppMessages.REGISTRATION_FAILED + e.getMessage());
        }
    }

    private int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                ConsoleColors.printError(AppMessages.PLEASE_ENTER_VALID_NUMBER);
            }
        }
    }
} 