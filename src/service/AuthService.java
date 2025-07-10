package service;

import model.User;
import model.JobSeeker;
import model.Recruiter;
import util.Validator;

public class AuthService {
    private UserService userService;

    public AuthService() {
        this.userService = new UserService();
    }

    public User authenticate(String email, String password) {
        try {
            return userService.login(email, password);
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }

    public JobSeeker registerJobSeeker(String name, String email, String password) {
        try {
            return userService.registerJobSeeker(name, email, password);
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }

    public Recruiter registerRecruiter(String name, String email, String password) {
        try {
            return userService.registerRecruiter(name, email, password);
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }

    public boolean isJobSeeker(User user) {
        return user instanceof JobSeeker;
    }

    public boolean isRecruiter(User user) {
        return user instanceof Recruiter;
    }

    public boolean isEmailAvailable(String email) {
        return !userService.isEmailRegistered(email);
    }

    public void validateEmail(String email) {
        if (!Validator.isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email format.");
        }
    }

    public void validatePassword(String password) {
        if (!Validator.isValidPassword(password)) {
            throw new IllegalArgumentException("Password must be at least 6 characters long.");
        }
    }

    public void validateName(String name) {
        if (!Validator.isValidName(name)) {
            throw new IllegalArgumentException("Name must be at least 2 characters long.");
        }
    }
} 