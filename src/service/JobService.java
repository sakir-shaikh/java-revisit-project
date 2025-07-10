package service;

import dao.JobDAO;
import model.Job;
import model.Company;
import model.Skill;
import util.Validator;

import java.util.List;

public class JobService {
    private JobDAO jobDAO;

    public JobService() {
        this.jobDAO = new JobDAO();
    }

    public Job createJob(String title, String description, Company company) {
        // Validate input
        if (!Validator.isValidJobTitle(title)) {
            throw new IllegalArgumentException("Invalid job title. Title must be at least 3 characters long.");
        }
        
        if (!Validator.isValidJobDescription(description)) {
            throw new IllegalArgumentException("Invalid job description. Description must be at least 10 characters long.");
        }
        
        if (company == null) {
            throw new IllegalArgumentException("Company cannot be null.");
        }

        return jobDAO.createJob(title, description, company);
    }

    public Job getJobById(Long id) {
        if (!Validator.isValidId(id)) {
            throw new IllegalArgumentException("Invalid job ID.");
        }
        return jobDAO.getJobById(id);
    }

    public List<Job> getAllJobs() {
        return jobDAO.getAllJobs();
    }

    public List<Job> getJobsByCompany(Long companyId) {
        if (!Validator.isValidId(companyId)) {
            throw new IllegalArgumentException("Invalid company ID.");
        }
        return jobDAO.getJobsByCompany(companyId);
    }

    public List<Job> searchJobsByTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Search title cannot be empty.");
        }
        return jobDAO.searchJobsByTitle(title);
    }

    public List<Job> searchJobsBySkill(String skillName) {
        if (skillName == null || skillName.trim().isEmpty()) {
            throw new IllegalArgumentException("Skill name cannot be empty.");
        }
        return jobDAO.searchJobsBySkill(skillName);
    }

    public void addRequiredSkillToJob(Long jobId, Skill skill) {
        if (!Validator.isValidId(jobId)) {
            throw new IllegalArgumentException("Invalid job ID.");
        }
        
        if (skill == null) {
            throw new IllegalArgumentException("Skill cannot be null.");
        }
        
        jobDAO.addRequiredSkillToJob(jobId, skill);
    }

    public void deleteJob(Long jobId) {
        if (!Validator.isValidId(jobId)) {
            throw new IllegalArgumentException("Invalid job ID.");
        }
        jobDAO.deleteJob(jobId);
    }
} 