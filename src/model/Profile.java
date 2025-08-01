package model;

import java.util.ArrayList;
import java.util.List;

public class Profile {
    private Long id;
    private String summary;
    private List<Skill> skills;
    
    // Default constructor
    public Profile() {
        this.skills = new ArrayList<>();
    }
    
    // Full constructor
    public Profile(Long id, String summary) {
        this.id = id;
        this.summary = summary;
        this.skills = new ArrayList<>();
    }
    
    // Manual getters and setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getSummary() {
        return summary;
    }
    
    public void setSummary(String summary) {
        this.summary = summary;
    }
    
    public List<Skill> getSkills() {
        return skills;
    }
    
    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }
} 