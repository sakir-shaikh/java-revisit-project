package service;

import dao.UserDAO;
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
        User user = userDAO.authenticateUser(email, password);
        if (user == null) {
            throw new IllegalArgumentException(AppMessages.LOGIN_FAILED + "Invalid email or password.");
        }
        return user;
    }

    public JobSeeker getJobSeekerById(Long id) {
        if (!Validator.isValidId(id)) {
            throw new IllegalArgumentException(AppMessages.INVALID_ID);
        }
        return userDAO.getJobSeekerById(id);
    }

    public Recruiter getRecruiterById(Long id) {
        if (!Validator.isValidId(id)) {
            throw new IllegalArgumentException(AppMessages.INVALID_ID);
        }
        return userDAO.getRecruiterById(id);
    }

    public boolean isEmailRegistered(String email) {
        return userDAO.emailExists(email);
    }
} 