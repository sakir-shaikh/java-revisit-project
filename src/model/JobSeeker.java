package model;

import java.util.ArrayList;
import java.util.List;

public class JobSeeker extends User {
    private Profile profile;
    private List<Application> applications;
    private List<Skill> skills;

    public JobSeeker() {
        super();
        this.applications = new ArrayList<>();
        this.skills = new ArrayList<>();
        setUserType("JOBSEEKER");
    }

    public JobSeeker(Long id, String name, String email, String password) {
        super(id, name, email, password, "JOBSEEKER");
        this.applications = new ArrayList<>();
        this.skills = new ArrayList<>();
    }

    // Getters and Setters
    public Profile getProfile() { return profile; }
    public void setProfile(Profile profile) { this.profile = profile; }

    public List<Application> getApplications() { return applications; }
    public void setApplications(List<Application> applications) { this.applications = applications; }

    public List<Skill> getSkills() { return skills; }
    public void setSkills(List<Skill> skills) { this.skills = skills; }

    public void addSkill(Skill skill) {
        if (this.skills == null) {
            this.skills = new ArrayList<>();
        }
        this.skills.add(skill);
    }

    public void addApplication(Application application) {
        if (this.applications == null) {
            this.applications = new ArrayList<>();
        }
        this.applications.add(application);
    }
} 