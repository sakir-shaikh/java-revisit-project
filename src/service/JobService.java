package service;

import dao.JobDAO;
import model.Job;
import model.Company;
import model.Skill;
import util.Validator;
import util.AppMessages;

import java.util.List;

public class JobService {
    private JobDAO jobDAO;

    public JobService() {
        this.jobDAO = new JobDAO();
    }

    public Job createJob(String title, String description, Company company) {
        // Validate input
        if (!Validator.isValidJobTitle(title)) {
            throw new IllegalArgumentException(AppMessages.INVALID_JOB_TITLE);
        }
        
        if (!Validator.isValidJobDescription(description)) {
            throw new IllegalArgumentException(AppMessages.INVALID_JOB_DESCRIPTION);
        }
        
        if (company == null) {
            throw new IllegalArgumentException(AppMessages.COMPANY_CANNOT_BE_NULL);
        }

        return jobDAO.createJob(title, description, company);
    }

    public Job getJobById(Long id) {
        if (!Validator.isValidId(id)) {
            throw new IllegalArgumentException(AppMessages.INVALID_JOB_ID);
        }
        return jobDAO.getJobById(id);
    }

    public List<Job> getAllJobs() {
        return jobDAO.getAllJobs();
    }

    public List<Job> getJobsByCompany(Long companyId) {
        if (!Validator.isValidId(companyId)) {
            throw new IllegalArgumentException(AppMessages.INVALID_ID);
        }
        return jobDAO.getJobsByCompany(companyId);
    }

    public List<Job> searchJobsByTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException(AppMessages.SEARCH_TITLE_EMPTY);
        }
        return jobDAO.searchJobsByTitle(title);
    }

    public List<Job> searchJobsBySkill(String skillName) {
        if (skillName == null || skillName.trim().isEmpty()) {
            throw new IllegalArgumentException(AppMessages.SKILL_NAME_EMPTY);
        }
        return jobDAO.searchJobsBySkill(skillName);
    }

    public void addRequiredSkillToJob(Long jobId, Skill skill) {
        if (!Validator.isValidId(jobId)) {
            throw new IllegalArgumentException(AppMessages.INVALID_JOB_ID);
        }
        
        if (skill == null) {
            throw new IllegalArgumentException(AppMessages.SKILL_CANNOT_BE_NULL);
        }
        
        jobDAO.addRequiredSkillToJob(jobId, skill);
    }

    public void deleteJob(Long jobId) {
        if (!Validator.isValidId(jobId)) {
            throw new IllegalArgumentException(AppMessages.INVALID_JOB_ID);
        }
        jobDAO.deleteJob(jobId);
    }
} 