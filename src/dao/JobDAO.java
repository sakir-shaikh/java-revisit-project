package dao;

import model.Job;
import model.Company;
import model.Skill;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JobDAO {
    private Database database;

    public JobDAO() {
        this.database = Database.getInstance();
    }

    public Job createJob(String title, String description, Company company) {
        Job job = new Job(database.getNextJobId(), title, description, company);
        database.addJob(job);
        return job;
    }

    public Job getJobById(Long id) {
        return database.getJobs().get(id);
    }

    public List<Job> getAllJobs() {
        return new ArrayList<>(database.getJobs().values());
    }

    public List<Job> getJobsByCompany(Long companyId) {
        return database.getJobs().values().stream()
                .filter(job -> job.getCompany() != null && job.getCompany().getId().equals(companyId))
                .collect(Collectors.toList());
    }

    public List<Job> searchJobsByTitle(String title) {
        return database.getJobs().values().stream()
                .filter(job -> job.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Job> searchJobsBySkill(String skillName) {
        return database.getJobs().values().stream()
                .filter(job -> job.getRequiredSkills().stream()
                        .anyMatch(skill -> skill.getName().toLowerCase().contains(skillName.toLowerCase())))
                .collect(Collectors.toList());
    }

    public void addRequiredSkillToJob(Long jobId, Skill skill) {
        Job job = getJobById(jobId);
        if (job != null) {
            job.addRequiredSkill(skill);
            database.addJob(job); // Update the job
        }
    }

    public void deleteJob(Long jobId) {
        database.getJobs().remove(jobId);
    }
} 