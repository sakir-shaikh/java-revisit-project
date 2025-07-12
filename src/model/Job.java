package model;

import java.util.ArrayList;
import java.util.List;

public class Job {
    private Long id;
    private String title;
    private String description;
    private Company company;
    private List<Application> applications;
    private List<Skill> requiredSkills;
    
    // Default constructor
    public Job() {
        this.applications = new ArrayList<>();
        this.requiredSkills = new ArrayList<>();
    }
    
    // Full constructor
    public Job(Long id, String title, String description, Company company) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.company = company;
        this.applications = new ArrayList<>();
        this.requiredSkills = new ArrayList<>();
    }
    
    // Manual getters and setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public Company getCompany() {
        return company;
    }
    
    public void setCompany(Company company) {
        this.company = company;
    }
    
    public List<Application> getApplications() {
        return applications;
    }
    
    public void setApplications(List<Application> applications) {
        this.applications = applications;
    }
    
    public List<Skill> getRequiredSkills() {
        return requiredSkills;
    }
    
    public void setRequiredSkills(List<Skill> requiredSkills) {
        this.requiredSkills = requiredSkills;
    }
    
    // Utility methods
    public void addRequiredSkill(Skill skill) {
        if (this.requiredSkills == null) {
            this.requiredSkills = new ArrayList<>();
        }
        this.requiredSkills.add(skill);
    }
    
    public void addApplication(Application application) {
        if (this.applications == null) {
            this.applications = new ArrayList<>();
        }
        this.applications.add(application);
    }
} 