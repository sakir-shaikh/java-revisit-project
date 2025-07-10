package dao;

import model.Application;
import model.JobSeeker;
import model.Job;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ApplicationDAO {
    private Database database;

    public ApplicationDAO() {
        this.database = Database.getInstance();
    }

    public Application createApplication(JobSeeker jobSeeker, Job job) {
        Application application = new Application(database.getNextApplicationId(), jobSeeker, job);
        database.addApplication(application);
        
        // Update the job seeker's applications
        jobSeeker.addApplication(application);
        database.addJobSeeker(jobSeeker);
        
        // Update the job's applications
        job.addApplication(application);
        database.addJob(job);
        
        return application;
    }

    public Application getApplicationById(Long id) {
        return database.getApplications().get(id);
    }

    public List<Application> getAllApplications() {
        return new ArrayList<>(database.getApplications().values());
    }

    public List<Application> getApplicationsByJobSeeker(Long jobSeekerId) {
        return database.getApplications().values().stream()
                .filter(app -> app.getJobSeeker().getId().equals(jobSeekerId))
                .collect(Collectors.toList());
    }

    public List<Application> getApplicationsByJob(Long jobId) {
        return database.getApplications().values().stream()
                .filter(app -> app.getJob().getId().equals(jobId))
                .collect(Collectors.toList());
    }

    public List<Application> getApplicationsByStatus(String status) {
        return database.getApplications().values().stream()
                .filter(app -> app.getStatus().equals(status))
                .collect(Collectors.toList());
    }

    public void updateApplicationStatus(Long applicationId, String status) {
        Application application = getApplicationById(applicationId);
        if (application != null) {
            application.setStatus(status);
            database.addApplication(application);
        }
    }

    public boolean hasApplied(Long jobSeekerId, Long jobId) {
        return database.getApplications().values().stream()
                .anyMatch(app -> app.getJobSeeker().getId().equals(jobSeekerId) 
                        && app.getJob().getId().equals(jobId));
    }

    public void deleteApplication(Long applicationId) {
        Application application = getApplicationById(applicationId);
        if (application != null) {
            // Remove from job seeker's applications
            application.getJobSeeker().getApplications().removeIf(app -> app.getId().equals(applicationId));
            database.addJobSeeker(application.getJobSeeker());
            
            // Remove from job's applications
            application.getJob().getApplications().removeIf(app -> app.getId().equals(applicationId));
            database.addJob(application.getJob());
            
            // Remove from applications
            database.getApplications().remove(applicationId);
        }
    }
} 