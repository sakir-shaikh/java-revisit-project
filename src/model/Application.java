package model;

import java.util.Date;

public class Application {
    private Long id;
    private JobSeeker jobSeeker;
    private Job job;
    private String status; // "PENDING", "ACCEPTED", "REJECTED"
    private Date appliedDate;
    
    // Default constructor
    public Application() {
        this.appliedDate = new Date();
    }
    
    // Full constructor
    public Application(Long id, JobSeeker jobSeeker, Job job) {
        this.id = id;
        this.jobSeeker = jobSeeker;
        this.job = job;
        this.status = "PENDING";
        this.appliedDate = new Date();
    }
    
    // Manual getters and setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public JobSeeker getJobSeeker() {
        return jobSeeker;
    }
    
    public void setJobSeeker(JobSeeker jobSeeker) {
        this.jobSeeker = jobSeeker;
    }
    
    public Job getJob() {
        return job;
    }
    
    public void setJob(Job job) {
        this.job = job;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public Date getAppliedDate() {
        return appliedDate;
    }
    
    public void setAppliedDate(Date appliedDate) {
        this.appliedDate = appliedDate;
    }
} 