package service;

import dao.ApplicationDAO;
import model.Application;
import model.JobSeeker;
import model.Job;
import util.Validator;

import java.util.List;

public class ApplicationService {
    private ApplicationDAO applicationDAO;

    public ApplicationService() {
        this.applicationDAO = new ApplicationDAO();
    }

    public Application applyForJob(JobSeeker jobSeeker, Job job) {
        // Validate input
        if (jobSeeker == null) {
            throw new IllegalArgumentException("Job seeker cannot be null.");
        }
        
        if (job == null) {
            throw new IllegalArgumentException("Job cannot be null.");
        }

        // Check if already applied
        if (applicationDAO.hasApplied(jobSeeker.getId(), job.getId())) {
            throw new IllegalArgumentException("You have already applied for this job.");
        }

        return applicationDAO.createApplication(jobSeeker, job);
    }

    public Application getApplicationById(Long id) {
        if (!Validator.isValidId(id)) {
            throw new IllegalArgumentException("Invalid application ID.");
        }
        return applicationDAO.getApplicationById(id);
    }

    public List<Application> getApplicationsByJobSeeker(Long jobSeekerId) {
        if (!Validator.isValidId(jobSeekerId)) {
            throw new IllegalArgumentException("Invalid job seeker ID.");
        }
        return applicationDAO.getApplicationsByJobSeeker(jobSeekerId);
    }

    public List<Application> getApplicationsByJob(Long jobId) {
        if (!Validator.isValidId(jobId)) {
            throw new IllegalArgumentException("Invalid job ID.");
        }
        return applicationDAO.getApplicationsByJob(jobId);
    }

    public List<Application> getAllApplications() {
        return applicationDAO.getAllApplications();
    }

    public List<Application> getApplicationsByStatus(String status) {
        if (status == null || status.trim().isEmpty()) {
            throw new IllegalArgumentException("Status cannot be empty.");
        }
        return applicationDAO.getApplicationsByStatus(status);
    }

    public void updateApplicationStatus(Long applicationId, String status) {
        if (!Validator.isValidId(applicationId)) {
            throw new IllegalArgumentException("Invalid application ID.");
        }
        
        if (status == null || status.trim().isEmpty()) {
            throw new IllegalArgumentException("Status cannot be empty.");
        }
        
        // Validate status values
        if (!status.equals("PENDING") && !status.equals("ACCEPTED") && !status.equals("REJECTED")) {
            throw new IllegalArgumentException("Invalid status. Must be PENDING, ACCEPTED, or REJECTED.");
        }
        
        applicationDAO.updateApplicationStatus(applicationId, status);
    }

    public boolean hasApplied(Long jobSeekerId, Long jobId) {
        if (!Validator.isValidId(jobSeekerId)) {
            throw new IllegalArgumentException("Invalid job seeker ID.");
        }
        
        if (!Validator.isValidId(jobId)) {
            throw new IllegalArgumentException("Invalid job ID.");
        }
        
        return applicationDAO.hasApplied(jobSeekerId, jobId);
    }

    public void withdrawApplication(Long applicationId) {
        if (!Validator.isValidId(applicationId)) {
            throw new IllegalArgumentException("Invalid application ID.");
        }
        applicationDAO.deleteApplication(applicationId);
    }
} 