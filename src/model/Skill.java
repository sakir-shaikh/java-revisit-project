package model;

public class Skill {
    private Long id;
    private String name;
    
    // Default constructor
    public Skill() {
    }
    
    // Full constructor
    public Skill(Long id, String name) {
        this.id = id;
        this.name = name;
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
} 