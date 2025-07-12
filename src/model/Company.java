package model;

import java.util.ArrayList;
import java.util.List;

public class Company {
    private Long id;
    private String name;
    private String description;
    private List<Job> jobs;
    
    // Default constructor
    public Company() {
        this.jobs = new ArrayList<>();
    }
    
    // Full constructor
    public Company(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.jobs = new ArrayList<>();
    }
    
    // Manual getters and setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public List<Job> getJobs() {
        return jobs;
    }
    
    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }
    
    // Utility method
    public void addJob(Job job) {
        if (this.jobs == null) {
            this.jobs = new ArrayList<>();
        }
        this.jobs.add(job);
    }
} 