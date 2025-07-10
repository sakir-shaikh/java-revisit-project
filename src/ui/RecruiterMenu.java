package ui;

import model.Recruiter;
import model.Job;
import model.Application;
import model.Company;
import service.JobService;
import service.ApplicationService;
import util.ConsoleColors;

import java.util.List;
import java.util.Scanner;

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
            showMenu();
            int choice = getIntInput("Enter your choice: ");
            
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
                    ConsoleColors.printInfo("Logging out...");
                    return;
                default:
                    ConsoleColors.printError("Invalid choice. Please try again.");
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
        
        System.out.print("Enter job title: ");
        String title = scanner.nextLine();
        
        System.out.print("Enter job description: ");
        String description = scanner.nextLine();
        
        // For simplicity, we'll create a default company
        Company company = new Company(1L, "Default Company", "A sample company");
        
        try {
            Job job = jobService.createJob(title, description, company);
            ConsoleColors.printSuccess("Job created successfully! Job ID: " + job.getId());
        } catch (IllegalArgumentException e) {
            ConsoleColors.printError("Job creation failed: " + e.getMessage());
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
        
        System.out.print("Enter application ID: ");
        Long applicationId = getLongInput("Enter application ID: ");
        
        System.out.println("Enter new status:");
        System.out.println("1. PENDING");
        System.out.println("2. ACCEPTED");
        System.out.println("3. REJECTED");
        
        int statusChoice = getIntInput("Enter status choice: ");
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
                ConsoleColors.printError("Invalid status choice.");
                return;
        }
        
        try {
            applicationService.updateApplicationStatus(applicationId, status);
            ConsoleColors.printSuccess("Application status updated successfully!");
        } catch (IllegalArgumentException e) {
            ConsoleColors.printError("Status update failed: " + e.getMessage());
        }
    }

    private void deleteJob() {
        ConsoleColors.printTitle("DELETE JOB");
        
        System.out.print("Enter job ID to delete: ");
        Long jobId = getLongInput("Enter job ID: ");
        
        try {
            jobService.deleteJob(jobId);
            ConsoleColors.printSuccess("Job deleted successfully!");
        } catch (IllegalArgumentException e) {
            ConsoleColors.printError("Job deletion failed: " + e.getMessage());
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
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                ConsoleColors.printError("Please enter a valid number.");
            }
        }
    }

    private Long getLongInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Long.parseLong(scanner.nextLine());
            } catch (NumberFormatException e) {
                ConsoleColors.printError("Please enter a valid number.");
            }
        }
    }
} 