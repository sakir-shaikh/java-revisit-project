package dao;

import model.JobSeeker;
import model.Recruiter;
import model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserDAO {
    private Database database;

    public UserDAO() {
        this.database = Database.getInstance();
    }

    // JobSeeker operations
    public JobSeeker createJobSeeker(String name, String email, String password) {
        JobSeeker jobSeeker = new JobSeeker(database.getNextJobSeekerId(), name, email, password);
        database.addJobSeeker(jobSeeker);
        return jobSeeker;
    }

    public JobSeeker getJobSeekerById(Long id) {
        return database.getJobSeekers().get(id);
    }

    public List<JobSeeker> getAllJobSeekers() {
        return new ArrayList<>(database.getJobSeekers().values());
    }

    public JobSeeker getJobSeekerByEmail(String email) {
        for (JobSeeker jobSeeker : database.getJobSeekers().values()) {
            if (jobSeeker.getEmail().equals(email)) {
                return jobSeeker;
            }
        }
        return null;
    }

    // Recruiter operations
    public Recruiter createRecruiter(String name, String email, String password) {
        Recruiter recruiter = new Recruiter(database.getNextRecruiterId(), name, email, password, null);
        database.addRecruiter(recruiter);
        return recruiter;
    }

    public Recruiter getRecruiterById(Long id) {
        return database.getRecruiters().get(id);
    }

    public List<Recruiter> getAllRecruiters() {
        return new ArrayList<>(database.getRecruiters().values());
    }

    public Recruiter getRecruiterByEmail(String email) {
        for (Recruiter recruiter : database.getRecruiters().values()) {
            if (recruiter.getEmail().equals(email)) {
                return recruiter;
            }
        }
        return null;
    }

    // Authentication
    public User authenticateUser(String email, String password) {
        // Check job seekers
        JobSeeker jobSeeker = getJobSeekerByEmail(email);
        if (jobSeeker != null && jobSeeker.getPassword().equals(password)) {
            return jobSeeker;
        }

        // Check recruiters
        Recruiter recruiter = getRecruiterByEmail(email);
        if (recruiter != null && recruiter.getPassword().equals(password)) {
            return recruiter;
        }

        return null;
    }

    public boolean emailExists(String email) {
        return getJobSeekerByEmail(email) != null || getRecruiterByEmail(email) != null;
    }
} 