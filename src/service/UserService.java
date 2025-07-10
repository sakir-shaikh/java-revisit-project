package service;

import dao.UserDAO;
import model.JobSeeker;
import model.Recruiter;
import model.User;
import util.Validator;

public class UserService {
    private UserDAO userDAO;

    public UserService() {
        this.userDAO = new UserDAO();
    }

    public JobSeeker registerJobSeeker(String name, String email, String password) {
        // Validate input
        if (!Validator.isValidName(name)) {
            throw new IllegalArgumentException("Invalid name. Name must be at least 2 characters long.");
        }
        
        if (!Validator.isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email format.");
        }
        
        if (!Validator.isValidPassword(password)) {
            throw new IllegalArgumentException("Invalid password. Password must be at least 6 characters long.");
        }

        // Check if email already exists
        if (userDAO.emailExists(email)) {
            throw new IllegalArgumentException("Email already registered.");
        }

        // Create job seeker
        return userDAO.createJobSeeker(name, email, password);
    }

    public Recruiter registerRecruiter(String name, String email, String password) {
        // Validate input
        if (!Validator.isValidName(name)) {
            throw new IllegalArgumentException("Invalid name. Name must be at least 2 characters long.");
        }
        
        if (!Validator.isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email format.");
        }
        
        if (!Validator.isValidPassword(password)) {
            throw new IllegalArgumentException("Invalid password. Password must be at least 6 characters long.");
        }

        // Check if email already exists
        if (userDAO.emailExists(email)) {
            throw new IllegalArgumentException("Email already registered.");
        }

        // Create recruiter
        return userDAO.createRecruiter(name, email, password);
    }

    public User login(String email, String password) {
        if (!Validator.isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email format.");
        }
        
        if (!Validator.isValidPassword(password)) {
            throw new IllegalArgumentException("Invalid password.");
        }

        User user = userDAO.authenticateUser(email, password);
        if (user == null) {
            throw new IllegalArgumentException("Invalid email or password.");
        }

        return user;
    }

    public JobSeeker getJobSeekerById(Long id) {
        if (!Validator.isValidId(id)) {
            throw new IllegalArgumentException("Invalid job seeker ID.");
        }
        return userDAO.getJobSeekerById(id);
    }

    public Recruiter getRecruiterById(Long id) {
        if (!Validator.isValidId(id)) {
            throw new IllegalArgumentException("Invalid recruiter ID.");
        }
        return userDAO.getRecruiterById(id);
    }

    public boolean isEmailRegistered(String email) {
        return userDAO.emailExists(email);
    }
} 