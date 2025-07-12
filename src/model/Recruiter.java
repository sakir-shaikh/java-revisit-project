package model;

import java.util.ArrayList;
import java.util.List;

public class Recruiter extends User {
    private Company company;
    private List<Post> posts;
    
    // Default constructor
    public Recruiter() {
        super();
        this.posts = new ArrayList<>();
    }
    
    // Custom constructor for parent class fields
    public Recruiter(Long id, String name, String email, String password, Company company) {
        super(id, name, email, password, "RECRUITER");
        this.company = company;
        this.posts = new ArrayList<>();
    }
    
    // Manual getters and setters
    public Company getCompany() {
        return company;
    }
    
    public void setCompany(Company company) {
        this.company = company;
    }
    
    public List<Post> getPosts() {
        return posts;
    }
    
    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
    
    // Utility methods
    public void addPost(Post post) {
        if (this.posts == null) {
            this.posts = new ArrayList<>();
        }
        this.posts.add(post);
    }
} 