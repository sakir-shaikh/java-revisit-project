package model;

public class Post {
    private Long id;
    private String content;
    private Recruiter recruiter;
    
    // Default constructor
    public Post() {
    }
    
    // Full constructor
    public Post(Long id, String content, Recruiter recruiter) {
        this.id = id;
        this.content = content;
        this.recruiter = recruiter;
    }
    
    // Manual getters and setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    public Recruiter getRecruiter() {
        return recruiter;
    }
    
    public void setRecruiter(Recruiter recruiter) {
        this.recruiter = recruiter;
    }
} 