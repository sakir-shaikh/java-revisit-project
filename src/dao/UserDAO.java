package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import model.JobSeeker;
import model.Recruiter;
import model.User;
import util.AppMessages;

public class UserDAO {
    private Database database;
    
    // Email validation pattern
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
        "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
    );
    
    // Password validation pattern (at least 6 characters, 1 uppercase, 1 lowercase, 1 digit)
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(
        "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{6,}$"
    );

    public UserDAO() {
        this.database = Database.getInstance();
    }

    // JobSeeker operations
    public JobSeeker createJobSeeker(String name, String email, String password) {
        validateUserInput(name, email, password);
        
        JobSeeker jobSeeker = new JobSeeker(
            database.getNextJobSeekerId(), 
            name.trim(), 
            email.trim().toLowerCase(), 
            password
        );
        
        database.addJobSeeker(jobSeeker);
        return jobSeeker;
    }

    public Optional<JobSeeker> getJobSeekerById(Long id) {
        if (id == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(database.getJobSeekers().get(id));
    }

    public List<JobSeeker> getAllJobSeekers() {
        return new ArrayList<>(database.getJobSeekers().values());
    }

    public Optional<JobSeeker> getJobSeekerByEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return Optional.empty();
        }
        
        String normalizedEmail = email.trim().toLowerCase();
        return database.getJobSeekers().values().stream()
                .filter(jobSeeker -> jobSeeker.getEmail().equals(normalizedEmail))
                .findFirst();
    }

    // Recruiter operations
    public Recruiter createRecruiter(String name, String email, String password) {
        validateUserInput(name, email, password);
        
        Recruiter recruiter = new Recruiter(
            database.getNextRecruiterId(), 
            name.trim(), 
            email.trim().toLowerCase(), 
            password, 
            null // Company will be set later
        );
        
        database.addRecruiter(recruiter);
        return recruiter;
    }

    public Optional<Recruiter> getRecruiterById(Long id) {
        if (id == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(database.getRecruiters().get(id));
    }

    public List<Recruiter> getAllRecruiters() {
        return new ArrayList<>(database.getRecruiters().values());
    }

    public Optional<Recruiter> getRecruiterByEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return Optional.empty();
        }
        
        String normalizedEmail = email.trim().toLowerCase();
        return database.getRecruiters().values().stream()
                .filter(recruiter -> recruiter.getEmail().equals(normalizedEmail))
                .findFirst();
    }

    // Authentication
    public Optional<User> authenticateUser(String email, String password) {
        if (email == null || password == null) {
            return Optional.empty();
        }

        String normalizedEmail = email.trim().toLowerCase();
        
        // Check job seekers
        Optional<JobSeeker> jobSeeker = getJobSeekerByEmail(normalizedEmail);
        if (jobSeeker.isPresent() && jobSeeker.get().getPassword().equals(password)) {
            return Optional.of(jobSeeker.get());
        }

        // Check recruiters
        Optional<Recruiter> recruiter = getRecruiterByEmail(normalizedEmail);
        if (recruiter.isPresent() && recruiter.get().getPassword().equals(password)) {
            return Optional.of(recruiter.get());
        }

        return Optional.empty();
    }

    public boolean emailExists(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        
        String normalizedEmail = email.trim().toLowerCase();
        return getJobSeekerByEmail(normalizedEmail).isPresent() || 
               getRecruiterByEmail(normalizedEmail).isPresent();
    }

    // Update operations
    public boolean updateJobSeeker(JobSeeker jobSeeker) {
        if (jobSeeker == null || jobSeeker.getId() == null) {
            return false;
        }
        
        if (database.getJobSeekers().containsKey(jobSeeker.getId())) {
            database.getJobSeekers().put(jobSeeker.getId(), jobSeeker);
            return true;
        }
        return false;
    }

    public boolean updateRecruiter(Recruiter recruiter) {
        if (recruiter == null || recruiter.getId() == null) {
            return false;
        }
        
        if (database.getRecruiters().containsKey(recruiter.getId())) {
            database.getRecruiters().put(recruiter.getId(), recruiter);
            return true;
        }
        return false;
    }

    // Delete operations
    public boolean deleteJobSeeker(Long id) {
        if (id == null) {
            return false;
        }
        return database.getJobSeekers().remove(id) != null;
    }

    public boolean deleteRecruiter(Long id) {
        if (id == null) {
            return false;
        }
        return database.getRecruiters().remove(id) != null;
    }
    
    // Validation methods
    private void validateUserInput(String name, String email, String password) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(AppMessages.INVALID_NAME);
        }
        if (name.trim().length() < 2) {
            throw new IllegalArgumentException(AppMessages.INVALID_NAME);
        }
        
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException(AppMessages.INVALID_EMAIL);
        }
        if (!EMAIL_PATTERN.matcher(email.trim()).matches()) {
            throw new IllegalArgumentException(AppMessages.INVALID_EMAIL);
        }
        
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException(AppMessages.INVALID_PASSWORD);
        }
        if (!PASSWORD_PATTERN.matcher(password).matches()) {
            throw new IllegalArgumentException(AppMessages.INVALID_PASSWORD);
        }
        
        // Check if email already exists
        if (emailExists(email)) {
            throw new IllegalArgumentException(AppMessages.EMAIL_ALREADY_REGISTERED);
        }
    }
    
    // Search and filter methods
    public List<JobSeeker> searchJobSeekersByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return new ArrayList<>();
        }
        
        String searchName = name.trim().toLowerCase();
        return database.getJobSeekers().values().stream()
                .filter(jobSeeker -> jobSeeker.getName().toLowerCase().contains(searchName))
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }
    
    public List<Recruiter> searchRecruitersByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return new ArrayList<>();
        }
        
        String searchName = name.trim().toLowerCase();
        return database.getRecruiters().values().stream()
                .filter(recruiter -> recruiter.getName().toLowerCase().contains(searchName))
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }
    
    // Statistics methods
    public long getTotalJobSeekers() {
        return database.getJobSeekers().size();
    }
    
    public long getTotalRecruiters() {
        return database.getRecruiters().size();
    }
    
    public long getTotalUsers() {
        return getTotalJobSeekers() + getTotalRecruiters();
    }
} 