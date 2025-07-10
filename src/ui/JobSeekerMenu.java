package ui;

import model.JobSeeker;
import model.Job;
import model.Application;
import service.JobService;
import service.ApplicationService;
import util.ConsoleColors;

import java.util.List;
import java.util.Scanner;

public class JobSeekerMenu {
    private JobSeeker jobSeeker;
    private JobService jobService;
    private ApplicationService applicationService;
    private Scanner scanner;

    public JobSeekerMenu(JobSeeker jobSeeker) {
        this.jobSeeker = jobSeeker;
        this.jobService = new JobService();
        this.applicationService = new ApplicationService();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        ConsoleColors.printTitle("JOB SEEKER DASHBOARD - " + jobSeeker.getName());
        
        while (true) {
            showMenu();
            int choice = getIntInput("Enter your choice: ");
            
            switch (choice) {
                case 1:
                    viewAllJobs();
                    break;
                case 2:
                    searchJobs();
                    break;
                case 3:
                    applyForJob();
                    break;
                case 4:
                    viewMyApplications();
                    break;
                case 5:
                    withdrawApplication();
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
        System.out.println("\n" + ConsoleColors.CYAN + "=== JOB SEEKER MENU ===" + ConsoleColors.RESET);
        System.out.println("1. View All Jobs");
        System.out.println("2. Search Jobs");
        System.out.println("3. Apply for Job");
        System.out.println("4. View My Applications");
        System.out.println("5. Withdraw Application");
        System.out.println("6. Logout");
        System.out.println();
    }

    private void viewAllJobs() {
        ConsoleColors.printTitle("ALL AVAILABLE JOBS");
        
        List<Job> jobs = jobService.getAllJobs();
        if (jobs.isEmpty()) {
            ConsoleColors.printWarning("No jobs available at the moment.");
            return;
        }
        
        for (Job job : jobs) {
            displayJob(job);
        }
    }

    private void searchJobs() {
        ConsoleColors.printTitle("SEARCH JOBS");
        
        System.out.println("1. Search by title");
        System.out.println("2. Search by skill");
        System.out.println("3. Back to menu");
        
        int choice = getIntInput("Enter your choice: ");
        
        switch (choice) {
            case 1:
                searchByTitle();
                break;
            case 2:
                searchBySkill();
                break;
            case 3:
                return;
            default:
                ConsoleColors.printError("Invalid choice.");
        }
    }

    private void searchByTitle() {
        System.out.print("Enter job title to search: ");
        String title = scanner.nextLine();
        
        try {
            List<Job> jobs = jobService.searchJobsByTitle(title);
            if (jobs.isEmpty()) {
                ConsoleColors.printWarning("No jobs found with title: " + title);
            } else {
                ConsoleColors.printTitle("SEARCH RESULTS");
                for (Job job : jobs) {
                    displayJob(job);
                }
            }
        } catch (IllegalArgumentException e) {
            ConsoleColors.printError("Search failed: " + e.getMessage());
        }
    }

    private void searchBySkill() {
        System.out.print("Enter skill to search: ");
        String skill = scanner.nextLine();
        
        try {
            List<Job> jobs = jobService.searchJobsBySkill(skill);
            if (jobs.isEmpty()) {
                ConsoleColors.printWarning("No jobs found with skill: " + skill);
            } else {
                ConsoleColors.printTitle("SEARCH RESULTS");
                for (Job job : jobs) {
                    displayJob(job);
                }
            }
        } catch (IllegalArgumentException e) {
            ConsoleColors.printError("Search failed: " + e.getMessage());
        }
    }

    private void applyForJob() {
        ConsoleColors.printTitle("APPLY FOR JOB");
        
        System.out.print("Enter job ID to apply: ");
        Long jobId = getLongInput("Enter job ID: ");
        
        try {
            Job job = jobService.getJobById(jobId);
            if (job == null) {
                ConsoleColors.printError("Job not found.");
                return;
            }
            
            if (applicationService.hasApplied(jobSeeker.getId(), jobId)) {
                ConsoleColors.printWarning("You have already applied for this job.");
                return;
            }
            
            Application application = applicationService.applyForJob(jobSeeker, job);
            ConsoleColors.printSuccess("Application submitted successfully! Application ID: " + application.getId());
            
        } catch (IllegalArgumentException e) {
            ConsoleColors.printError("Application failed: " + e.getMessage());
        }
    }

    private void viewMyApplications() {
        ConsoleColors.printTitle("MY APPLICATIONS");
        
        try {
            List<Application> applications = applicationService.getApplicationsByJobSeeker(jobSeeker.getId());
            if (applications.isEmpty()) {
                ConsoleColors.printWarning("You haven't applied for any jobs yet.");
                return;
            }
            
            for (Application app : applications) {
                displayApplication(app);
            }
        } catch (IllegalArgumentException e) {
            ConsoleColors.printError("Failed to load applications: " + e.getMessage());
        }
    }

    private void withdrawApplication() {
        ConsoleColors.printTitle("WITHDRAW APPLICATION");
        
        System.out.print("Enter application ID to withdraw: ");
        Long applicationId = getLongInput("Enter application ID: ");
        
        try {
            applicationService.withdrawApplication(applicationId);
            ConsoleColors.printSuccess("Application withdrawn successfully!");
        } catch (IllegalArgumentException e) {
            ConsoleColors.printError("Withdrawal failed: " + e.getMessage());
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