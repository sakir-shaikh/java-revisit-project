package model;

public abstract class User {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String userType; // "JOBSEEKER" or "RECRUITER"
    
    // Default constructor
    public User() {
    }
    
    // Custom constructor for inheritance compatibility
    public User(Long id, String name, String email, String password, String userType) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.userType = userType;
    }
    
    // Manual getters
    public Long getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getPassword() {
        return password;
    }
    
    public String getUserType() {
        return userType;
    }
    
    // Manual setters
    public void setId(Long id) {
        this.id = id;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public void setUserType(String userType) {
        this.userType = userType;
    }
} 