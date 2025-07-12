package service;

import dao.UserDAO;
import java.util.Optional;
import model.JobSeeker;
import model.Recruiter;
import model.User;
import util.AppMessages;
import util.Validator;

public class UserService {
    private UserDAO userDAO;

    public UserService() {
        this.userDAO = new UserDAO();
    }

    public JobSeeker registerJobSeeker(String name, String email, String password) {
        if (!Validator.isValidName(name)) {
            throw new IllegalArgumentException(AppMessages.INVALID_NAME);
        }
        if (!Validator.isValidEmail(email)) {
            throw new IllegalArgumentException(AppMessages.INVALID_EMAIL);
        }
        if (!Validator.isValidPassword(password)) {
            throw new IllegalArgumentException(AppMessages.INVALID_PASSWORD);
        }
        if (userDAO.emailExists(email)) {
            throw new IllegalArgumentException(AppMessages.EMAIL_ALREADY_REGISTERED);
        }
        return userDAO.createJobSeeker(name, email, password);
    }

    public Recruiter registerRecruiter(String name, String email, String password) {
        if (!Validator.isValidName(name)) {
            throw new IllegalArgumentException(AppMessages.INVALID_NAME);
        }
        if (!Validator.isValidEmail(email)) {
            throw new IllegalArgumentException(AppMessages.INVALID_EMAIL);
        }
        if (!Validator.isValidPassword(password)) {
            throw new IllegalArgumentException(AppMessages.INVALID_PASSWORD);
        }
        if (userDAO.emailExists(email)) {
            throw new IllegalArgumentException(AppMessages.EMAIL_ALREADY_REGISTERED);
        }
        return userDAO.createRecruiter(name, email, password);
    }

    public User login(String email, String password) {
        if (!Validator.isValidEmail(email)) {
            throw new IllegalArgumentException(AppMessages.INVALID_EMAIL);
        }
        if (!Validator.isValidPassword(password)) {
            throw new IllegalArgumentException(AppMessages.INVALID_PASSWORD);
        }
        
        Optional<User> userOptional = userDAO.authenticateUser(email, password);
        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException(AppMessages.LOGIN_FAILED + "Invalid email or password.");
        }
        return userOptional.get();
    }

    public JobSeeker getJobSeekerById(Long id) {
        if (!Validator.isValidId(id)) {
            throw new IllegalArgumentException(AppMessages.INVALID_ID);
        }
        
        Optional<JobSeeker> jobSeekerOptional = userDAO.getJobSeekerById(id);
        if (jobSeekerOptional.isEmpty()) {
            throw new IllegalArgumentException(AppMessages.INVALID_ID + " Job seeker not found.");
        }
        return jobSeekerOptional.get();
    }

    public Recruiter getRecruiterById(Long id) {
        if (!Validator.isValidId(id)) {
            throw new IllegalArgumentException(AppMessages.INVALID_ID);
        }
        
        Optional<Recruiter> recruiterOptional = userDAO.getRecruiterById(id);
        if (recruiterOptional.isEmpty()) {
            throw new IllegalArgumentException(AppMessages.INVALID_ID + " Recruiter not found.");
        }
        return recruiterOptional.get();
    }

    public boolean isEmailRegistered(String email) {
        return userDAO.emailExists(email);
    }
    
    // Additional methods for better Optional handling
    public Optional<JobSeeker> findJobSeekerById(Long id) {
        if (!Validator.isValidId(id)) {
            return Optional.empty();
        }
        return userDAO.getJobSeekerById(id);
    }
    
    public Optional<Recruiter> findRecruiterById(Long id) {
        if (!Validator.isValidId(id)) {
            return Optional.empty();
        }
        return userDAO.getRecruiterById(id);
    }
    
    public Optional<JobSeeker> findJobSeekerByEmail(String email) {
        if (!Validator.isValidEmail(email)) {
            return Optional.empty();
        }
        return userDAO.getJobSeekerByEmail(email);
    }
    
    public Optional<Recruiter> findRecruiterByEmail(String email) {
        if (!Validator.isValidEmail(email)) {
            return Optional.empty();
        }
        return userDAO.getRecruiterByEmail(email);
    }
    
    // Update methods
    public boolean updateJobSeeker(JobSeeker jobSeeker) {
        if (jobSeeker == null) {
            return false;
        }
        return userDAO.updateJobSeeker(jobSeeker);
    }
    
    public boolean updateRecruiter(Recruiter recruiter) {
        if (recruiter == null) {
            return false;
        }
        return userDAO.updateRecruiter(recruiter);
    }
    
    // Delete methods
    public boolean deleteJobSeeker(Long id) {
        if (!Validator.isValidId(id)) {
            return false;
        }
        return userDAO.deleteJobSeeker(id);
    }
    
    public boolean deleteRecruiter(Long id) {
        if (!Validator.isValidId(id)) {
            return false;
        }
        return userDAO.deleteRecruiter(id);
    }
    
    // Search methods
    public java.util.List<JobSeeker> searchJobSeekersByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return new java.util.ArrayList<>();
        }
        return userDAO.searchJobSeekersByName(name);
    }
    
    public java.util.List<Recruiter> searchRecruitersByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return new java.util.ArrayList<>();
        }
        return userDAO.searchRecruitersByName(name);
    }
    
    // Statistics methods
    public long getTotalJobSeekers() {
        return userDAO.getTotalJobSeekers();
    }
    
    public long getTotalRecruiters() {
        return userDAO.getTotalRecruiters();
    }
    
    public long getTotalUsers() {
        return userDAO.getTotalUsers();
    }
} 