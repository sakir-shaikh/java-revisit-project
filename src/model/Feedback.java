package model;

public class Feedback {
    private Long id;
    private String comment;
    private int rating; // 1-5 stars
    private JobSeeker jobSeeker;
    private Company company;

    public Feedback() {}

    public Feedback(Long id, String comment, int rating, JobSeeker jobSeeker, Company company) {
        this.id = id;
        this.comment = comment;
        this.rating = rating;
        this.jobSeeker = jobSeeker;
        this.company = company;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }

    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }

    public JobSeeker getJobSeeker() { return jobSeeker; }
    public void setJobSeeker(JobSeeker jobSeeker) { this.jobSeeker = jobSeeker; }

    public Company getCompany() { return company; }
    public void setCompany(Company company) { this.company = company; }
} 