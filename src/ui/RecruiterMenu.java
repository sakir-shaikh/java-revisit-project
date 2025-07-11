package ui;

import java.util.List;
import java.util.Scanner;
import model.Application;
import model.Company;
import model.Job;
import model.Recruiter;
import service.ApplicationService;
import service.JobService;
import util.AppMessages;
import util.ConsoleColors;
import util.InputUtils;
import util.MenuUtils;

public class RecruiterMenu {
    private Recruiter recruiter;
    private JobService jobService;
    private ApplicationService applicationService;
    private Scanner scanner;

    public RecruiterMenu(Recruiter recruiter) {
        this.recruiter = recruiter;
        this.jobService = new JobService();
        this.applicationService = new ApplicationService();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        ConsoleColors.printTitle("RECRUITER DASHBOARD - " + recruiter.getName());
        
        while (true) {
            MenuUtils.printMenu(AppMessages.MAIN_MENU,
                "View All Jobs",
                "Create New Job",
                "View Applications",
                "Update Application Status",
                "Delete Job",
                "Logout");
            int choice = InputUtils.getIntInput(scanner, AppMessages.ENTER_CHOICE);
            
            switch (choice) {
                case 1:
                    viewAllJobs();
                    break;
                case 2:
                    createJob();
                    break;
                case 3:
                    viewApplications();
                    break;
                case 4:
                    updateApplicationStatus();
                    break;
                case 5:
                    deleteJob();
                    break;
                case 6:
                    ConsoleColors.printInfo(AppMessages.LOGGING_OUT);
                    return;
                default:
                    ConsoleColors.printError(AppMessages.INVALID_CHOICE);
            }
        }
    }

    private void showMenu() {
        System.out.println("\n" + ConsoleColors.CYAN + "=== RECRUITER MENU ===" + ConsoleColors.RESET);
        System.out.println("1. View All Jobs");
        System.out.println("2. Create New Job");
        System.out.println("3. View Applications");
        System.out.println("4. Update Application Status");
        System.out.println("5. Delete Job");
        System.out.println("6. Logout");
        System.out.println();
    }

    private void viewAllJobs() {
        ConsoleColors.printTitle("ALL JOBS");
        
        List<Job> jobs = jobService.getAllJobs();
        if (jobs.isEmpty()) {
            ConsoleColors.printWarning("No jobs available at the moment.");
            return;
        }
        
        for (Job job : jobs) {
            displayJob(job);
        }
    }

    private void createJob() {
        ConsoleColors.printTitle("CREATE NEW JOB");
        
        String title = InputUtils.getStringInput(scanner, AppMessages.ENTER_JOB_TITLE);
        
        String description = InputUtils.getStringInput(scanner, AppMessages.ENTER_JOB_DESCRIPTION);
        
        // For simplicity, we'll create a default company
        Company company = new Company(1L, "Default Company", "A sample company");
        
        try {
            Job job = jobService.createJob(title, description, company);
            ConsoleColors.printSuccess(AppMessages.JOB_CREATED_SUCCESS + job.getId());
        } catch (IllegalArgumentException e) {
            ConsoleColors.printError(AppMessages.JOB_CREATION_FAILED + e.getMessage());
        }
    }

    private void viewApplications() {
        ConsoleColors.printTitle("ALL APPLICATIONS");
        
        List<Application> applications = applicationService.getAllApplications();
        if (applications.isEmpty()) {
            ConsoleColors.printWarning("No applications found.");
            return;
        }
        
        for (Application app : applications) {
            displayApplication(app);
        }
    }

    private void updateApplicationStatus() {
        ConsoleColors.printTitle("UPDATE APPLICATION STATUS");
        
        Long applicationId = InputUtils.getLongInput(scanner, AppMessages.ENTER_APPLICATION_ID);
        
        MenuUtils.printMenu("Enter new status:", "PENDING", "ACCEPTED", "REJECTED");
        int statusChoice = InputUtils.getIntInput(scanner, "Enter status choice: ");
        String status;
        
        switch (statusChoice) {
            case 1:
                status = "PENDING";
                break;
            case 2:
                status = "ACCEPTED";
                break;
            case 3:
                status = "REJECTED";
                break;
            default:
                ConsoleColors.printError(AppMessages.INVALID_CHOICE);
                return;
        }
        
        try {
            applicationService.updateApplicationStatus(applicationId, status);
            ConsoleColors.printSuccess(AppMessages.STATUS_UPDATE_SUCCESS);
        } catch (IllegalArgumentException e) {
            ConsoleColors.printError(AppMessages.STATUS_UPDATE_FAILED + e.getMessage());
        }
    }

    private void deleteJob() {
        ConsoleColors.printTitle("DELETE JOB");
        
        Long jobId = InputUtils.getLongInput(scanner, AppMessages.ENTER_JOB_ID);
        
        try {
            jobService.deleteJob(jobId);
            ConsoleColors.printSuccess(AppMessages.JOB_DELETED_SUCCESS);
        } catch (IllegalArgumentException e) {
            ConsoleColors.printError(AppMessages.JOB_DELETION_FAILED + e.getMessage());
        }
    }

    private void displayJob(Job job) {
        System.out.println(ConsoleColors.YELLOW + "Job ID: " + job.getId() + ConsoleColors.RESET);
        System.out.println("Title: " + job.getTitle());
        System.out.println("Description: " + job.getDescription());
        if (job.getCompany() != null) {
            System.out.println("Company: " + job.getCompany().getName());
        }
        System.out.println("Applications: " + job.getApplications().size());
        System.out.println(ConsoleColors.BLUE + "---" + ConsoleColors.RESET);
    }

    private void displayApplication(Application app) {
        System.out.println(ConsoleColors.YELLOW + "Application ID: " + app.getId() + ConsoleColors.RESET);
        System.out.println("Job Seeker: " + app.getJobSeeker().getName());
        System.out.println("Job: " + app.getJob().getTitle());
        System.out.println("Status: " + app.getStatus());
        System.out.println("Applied Date: " + app.getAppliedDate());
        System.out.println(ConsoleColors.BLUE + "---" + ConsoleColors.RESET);
    }

    private int getIntInput(String prompt) {
        return InputUtils.getIntInput(scanner, prompt);
    }

    private Long getLongInput(String prompt) {
        return InputUtils.getLongInput(scanner, prompt);
    }
} 