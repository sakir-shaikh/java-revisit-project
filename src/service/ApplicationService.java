package service;

import dao.ApplicationDAO;
import model.Application;
import model.JobSeeker;
import model.Job;
import util.Validator;
import util.AppMessages;

import java.util.List;

public class ApplicationService {
    private ApplicationDAO applicationDAO;

    public ApplicationService() {
        this.applicationDAO = new ApplicationDAO();
    }

    public Application applyForJob(JobSeeker jobSeeker, Job job) {
        if (jobSeeker == null) {
            throw new IllegalArgumentException(AppMessages.JOB_SEEKER_CANNOT_BE_NULL);
        }
        if (job == null) {
            throw new IllegalArgumentException(AppMessages.JOB_CANNOT_BE_NULL);
        }
        if (applicationDAO.hasApplied(jobSeeker.getId(), job.getId())) {
            throw new IllegalArgumentException(AppMessages.ALREADY_APPLIED);
        }
        return applicationDAO.createApplication(jobSeeker, job);
    }

    public Application getApplicationById(Long id) {
        if (!Validator.isValidId(id)) {
            throw new IllegalArgumentException(AppMessages.INVALID_APPLICATION_ID);
        }
        return applicationDAO.getApplicationById(id);
    }

    public List<Application> getApplicationsByJobSeeker(Long jobSeekerId) {
        if (!Validator.isValidId(jobSeekerId)) {
            throw new IllegalArgumentException(AppMessages.INVALID_ID);
        }
        return applicationDAO.getApplicationsByJobSeeker(jobSeekerId);
    }

    public List<Application> getApplicationsByJob(Long jobId) {
        if (!Validator.isValidId(jobId)) {
            throw new IllegalArgumentException(AppMessages.INVALID_JOB_ID);
        }
        return applicationDAO.getApplicationsByJob(jobId);
    }

    public List<Application> getAllApplications() {
        return applicationDAO.getAllApplications();
    }

    public List<Application> getApplicationsByStatus(String status) {
        if (status == null || status.trim().isEmpty()) {
            throw new IllegalArgumentException(AppMessages.STATUS_CANNOT_BE_EMPTY);
        }
        return applicationDAO.getApplicationsByStatus(status);
    }

    public void updateApplicationStatus(Long applicationId, String status) {
        if (!Validator.isValidId(applicationId)) {
            throw new IllegalArgumentException(AppMessages.INVALID_APPLICATION_ID);
        }
        if (status == null || status.trim().isEmpty()) {
            throw new IllegalArgumentException(AppMessages.STATUS_CANNOT_BE_EMPTY);
        }
        if (!status.equals("PENDING") && !status.equals("ACCEPTED") && !status.equals("REJECTED")) {
            throw new IllegalArgumentException(AppMessages.INVALID_STATUS);
        }
        applicationDAO.updateApplicationStatus(applicationId, status);
    }

    public boolean hasApplied(Long jobSeekerId, Long jobId) {
        if (!Validator.isValidId(jobSeekerId)) {
            throw new IllegalArgumentException(AppMessages.INVALID_ID);
        }
        if (!Validator.isValidId(jobId)) {
            throw new IllegalArgumentException(AppMessages.INVALID_JOB_ID);
        }
        return applicationDAO.hasApplied(jobSeekerId, jobId);
    }

    public void withdrawApplication(Long applicationId) {
        if (!Validator.isValidId(applicationId)) {
            throw new IllegalArgumentException(AppMessages.INVALID_APPLICATION_ID);
        }
        applicationDAO.deleteApplication(applicationId);
    }
} 