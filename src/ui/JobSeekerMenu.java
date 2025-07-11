package ui;

import model.JobSeeker;
import model.Job;
import model.Application;
import service.JobService;
import service.ApplicationService;
import util.ConsoleColors;
import util.InputUtils;
import util.MenuUtils;
import util.AppMessages;

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
            MenuUtils.printMenu(AppMessages.MAIN_MENU,
                "View All Jobs",
                "Search Jobs",
                "Apply for Job",
                "View My Applications",
                "Withdraw Application",
                "Logout");
            int choice = InputUtils.getIntInput(scanner, AppMessages.ENTER_CHOICE);
            
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
                    ConsoleColors.printInfo(AppMessages.LOGGING_OUT);
                    return;
                default:
                    ConsoleColors.printError(AppMessages.INVALID_CHOICE);
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
            ConsoleColors.printWarning(AppMessages.NO_JOBS_AVAILABLE);
            return;
        }
        
        for (Job job : jobs) {
            displayJob(job);
        }
    }

    private void searchJobs() {
        ConsoleColors.printTitle("SEARCH JOBS");
        MenuUtils.printMenu("Search Jobs", "Search by title", "Search by skill", "Back to menu");
        int choice = InputUtils.getIntInput(scanner, AppMessages.ENTER_CHOICE);
        
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
                ConsoleColors.printError(AppMessages.INVALID_CHOICE);
        }
    }

    private void searchByTitle() {
        String title = InputUtils.getStringInput(scanner, AppMessages.ENTER_JOB_TITLE);
        
        try {
            List<Job> jobs = jobService.searchJobsByTitle(title);
            if (jobs.isEmpty()) {
                ConsoleColors.printWarning(AppMessages.NO_JOBS_FOUND_TITLE + title);
            } else {
                ConsoleColors.printTitle("SEARCH RESULTS");
                for (Job job : jobs) {
                    displayJob(job);
                }
            }
        } catch (IllegalArgumentException e) {
            ConsoleColors.printError(AppMessages.SEARCH_FAILED + e.getMessage());
        }
    }

    private void searchBySkill() {
        String skill = InputUtils.getStringInput(scanner, AppMessages.ENTER_SKILL);
        
        try {
            List<Job> jobs = jobService.searchJobsBySkill(skill);
            if (jobs.isEmpty()) {
                ConsoleColors.printWarning(AppMessages.NO_JOBS_FOUND_SKILL + skill);
            } else {
                ConsoleColors.printTitle("SEARCH RESULTS");
                for (Job job : jobs) {
                    displayJob(job);
                }
            }
        } catch (IllegalArgumentException e) {
            ConsoleColors.printError(AppMessages.SEARCH_FAILED + e.getMessage());
        }
    }

    private void applyForJob() {
        ConsoleColors.printTitle("APPLY FOR JOB");
        
        Long jobId = InputUtils.getLongInput(scanner, AppMessages.ENTER_JOB_ID);
        
        try {
            Job job = jobService.getJobById(jobId);
            if (job == null) {
                ConsoleColors.printError(AppMessages.JOB_NOT_FOUND);
                return;
            }
            
            if (applicationService.hasApplied(jobSeeker.getId(), jobId)) {
                ConsoleColors.printWarning(AppMessages.ALREADY_APPLIED_WARNING);
                return;
            }
            
            Application application = applicationService.applyForJob(jobSeeker, job);
            ConsoleColors.printSuccess(AppMessages.APPLICATION_SUCCESS + application.getId());
            
        } catch (IllegalArgumentException e) {
            ConsoleColors.printError(AppMessages.APPLICATION_FAILED + e.getMessage());
        }
    }

    private void viewMyApplications() {
        ConsoleColors.printTitle("MY APPLICATIONS");
        
        try {
            List<Application> applications = applicationService.getApplicationsByJobSeeker(jobSeeker.getId());
            if (applications.isEmpty()) {
                ConsoleColors.printWarning(AppMessages.NO_APPLICATIONS_YET);
                return;
            }
            
            for (Application app : applications) {
                displayApplication(app);
            }
        } catch (IllegalArgumentException e) {
            ConsoleColors.printError(AppMessages.FAILED_TO_LOAD_APPLICATIONS + e.getMessage());
        }
    }

    private void withdrawApplication() {
        ConsoleColors.printTitle("WITHDRAW APPLICATION");
        
        Long applicationId = InputUtils.getLongInput(scanner, AppMessages.ENTER_APPLICATION_ID);
        
        try {
            applicationService.withdrawApplication(applicationId);
            ConsoleColors.printSuccess(AppMessages.WITHDRAW_SUCCESS);
        } catch (IllegalArgumentException e) {
            ConsoleColors.printError(AppMessages.WITHDRAW_FAILED + e.getMessage());
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
        return InputUtils.getIntInput(scanner, prompt);
    }

    private Long getLongInput(String prompt) {
        return InputUtils.getLongInput(scanner, prompt);
    }
} 