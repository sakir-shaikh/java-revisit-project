package ui;

import java.util.Scanner;
import model.JobSeeker;
import model.Recruiter;
import model.User;
import service.AuthService;
import util.AppMessages;
import util.ConsoleColors;
import util.InputUtils;
import util.MenuUtils;

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
            MenuUtils.printMenu(AppMessages.MAIN_MENU, AppMessages.LOGIN, AppMessages.REGISTER, AppMessages.EXIT_OPTION);
            int choice = InputUtils.getIntInput(scanner, AppMessages.ENTER_CHOICE);
            
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

    private void login() {
        ConsoleColors.printTitle(AppMessages.LOGIN);
        
        String email = InputUtils.getStringInput(scanner, AppMessages.ENTER_EMAIL);
        
        String password = InputUtils.getStringInput(scanner, AppMessages.ENTER_PASSWORD);
        
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
        ConsoleColors.printTitle(AppMessages.REGISTER);
        
        System.out.println("1. Register as Job Seeker");
        System.out.println("2. Register as Recruiter");
        System.out.println("3. Back to Main Menu");
        
        int choice = InputUtils.getIntInput(scanner, AppMessages.ENTER_CHOICE);
        
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
        
        String name = InputUtils.getStringInput(scanner, AppMessages.ENTER_NAME);
        
        String email = InputUtils.getStringInput(scanner, AppMessages.ENTER_EMAIL);
        
        String password = InputUtils.getStringInput(scanner, AppMessages.ENTER_PASSWORD);
        
        try {
            JobSeeker jobSeeker = authService.registerJobSeeker(name, email, password);
            ConsoleColors.printSuccess(AppMessages.REGISTRATION_SUCCESS + jobSeeker.getName() + "!");
        } catch (IllegalArgumentException e) {
            ConsoleColors.printError(AppMessages.REGISTRATION_FAILED + e.getMessage());
        }
    }

    private void registerRecruiter() {
        ConsoleColors.printTitle("RECRUITER REGISTRATION");
        
        String name = InputUtils.getStringInput(scanner, AppMessages.ENTER_NAME);
        
        String email = InputUtils.getStringInput(scanner, AppMessages.ENTER_EMAIL);
        
        String password = InputUtils.getStringInput(scanner, AppMessages.ENTER_PASSWORD);
        
        try {
            Recruiter recruiter = authService.registerRecruiter(name, email, password);
            ConsoleColors.printSuccess(AppMessages.REGISTRATION_SUCCESS + recruiter.getName() + "!");
        } catch (IllegalArgumentException e) {
            ConsoleColors.printError(AppMessages.REGISTRATION_FAILED + e.getMessage());
        }
    }

    private int getIntInput(String prompt) {
        return InputUtils.getIntInput(scanner, prompt);
    }
} 