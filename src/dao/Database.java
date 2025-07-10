package dao;

import model.*;

import java.io.*;
import java.util.*;

public class Database {
    private static Database instance;
    
    // Collections to store data
    private Map<Long, JobSeeker> jobSeekers;
    private Map<Long, Recruiter> recruiters;
    private Map<Long, Job> jobs;
    private Map<Long, Application> applications;
    private Map<Long, Company> companies;
    private Map<Long, Skill> skills;
    private Map<Long, Post> posts;
    private Map<Long, Feedback> feedbacks;
    
    // ID counters
    private long nextJobSeekerId = 1;
    private long nextRecruiterId = 1;
    private long nextJobId = 1;
    private long nextApplicationId = 1;
    private long nextCompanyId = 1;
    private long nextSkillId = 1;
    private long nextPostId = 1;
    private long nextFeedbackId = 1;

    private Database() {
        initializeCollections();
        loadData();
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    private void initializeCollections() {
        jobSeekers = new HashMap<>();
        recruiters = new HashMap<>();
        jobs = new HashMap<>();
        applications = new HashMap<>();
        companies = new HashMap<>();
        skills = new HashMap<>();
        posts = new HashMap<>();
        feedbacks = new HashMap<>();
    }

    private void loadData() {
        // For now, we'll start with empty data
        // In a real implementation, you would parse the JSON file
        System.out.println("Database initialized with empty data");
        
        // Initialize with sample data if database is empty
        if (jobSeekers.isEmpty() && recruiters.isEmpty() && jobs.isEmpty()) {
            initializeSampleData();
        }
    }

    private void initializeSampleData() {
        System.out.println("Initializing database with sample data...");
        
        // Create sample skills
        Skill javaSkill = new Skill(getNextSkillId(), "Java");
        Skill pythonSkill = new Skill(getNextSkillId(), "Python");
        Skill javascriptSkill = new Skill(getNextSkillId(), "JavaScript");
        Skill sqlSkill = new Skill(getNextSkillId(), "SQL");
        Skill reactSkill = new Skill(getNextSkillId(), "React");
        Skill springSkill = new Skill(getNextSkillId(), "Spring Boot");
        Skill awsSkill = new Skill(getNextSkillId(), "AWS");
        Skill dockerSkill = new Skill(getNextSkillId(), "Docker");
        
        skills.put(javaSkill.getId(), javaSkill);
        skills.put(pythonSkill.getId(), pythonSkill);
        skills.put(javascriptSkill.getId(), javascriptSkill);
        skills.put(sqlSkill.getId(), sqlSkill);
        skills.put(reactSkill.getId(), reactSkill);
        skills.put(springSkill.getId(), springSkill);
        skills.put(awsSkill.getId(), awsSkill);
        skills.put(dockerSkill.getId(), dockerSkill);
        
        // Create sample companies
        Company techCorp = new Company(getNextCompanyId(), "TechCorp", "Leading technology company specializing in software development");
        Company dataSoft = new Company(getNextCompanyId(), "DataSoft", "Data analytics and machine learning solutions");
        Company webSolutions = new Company(getNextCompanyId(), "WebSolutions", "Web development and digital solutions");
        Company cloudTech = new Company(getNextCompanyId(), "CloudTech", "Cloud infrastructure and DevOps services");
        
        companies.put(techCorp.getId(), techCorp);
        companies.put(dataSoft.getId(), dataSoft);
        companies.put(webSolutions.getId(), webSolutions);
        companies.put(cloudTech.getId(), cloudTech);
        
        // Create sample job seekers
        JobSeeker johnDoe = new JobSeeker(getNextJobSeekerId(), "John Doe", "john@example.com", "password123");
        JobSeeker janeSmith = new JobSeeker(getNextJobSeekerId(), "Jane Smith", "jane@example.com", "password123");
        JobSeeker mikeJohnson = new JobSeeker(getNextJobSeekerId(), "Mike Johnson", "mike@example.com", "password123");
        
        // Add skills to job seekers
        johnDoe.addSkill(javaSkill);
        johnDoe.addSkill(springSkill);
        johnDoe.addSkill(sqlSkill);
        
        janeSmith.addSkill(pythonSkill);
        janeSmith.addSkill(awsSkill);
        janeSmith.addSkill(dockerSkill);
        
        mikeJohnson.addSkill(javascriptSkill);
        mikeJohnson.addSkill(reactSkill);
        mikeJohnson.addSkill(sqlSkill);
        
        jobSeekers.put(johnDoe.getId(), johnDoe);
        jobSeekers.put(janeSmith.getId(), janeSmith);
        jobSeekers.put(mikeJohnson.getId(), mikeJohnson);
        
        // Create sample recruiters
        Recruiter sarahRecruiter = new Recruiter(getNextRecruiterId(), "Sarah Wilson", "sarah@techcorp.com", "password123", techCorp);
        Recruiter tomRecruiter = new Recruiter(getNextRecruiterId(), "Tom Brown", "tom@datasoft.com", "password123", dataSoft);
        Recruiter lisaRecruiter = new Recruiter(getNextRecruiterId(), "Lisa Davis", "lisa@websolutions.com", "password123", webSolutions);
        
        recruiters.put(sarahRecruiter.getId(), sarahRecruiter);
        recruiters.put(tomRecruiter.getId(), tomRecruiter);
        recruiters.put(lisaRecruiter.getId(), lisaRecruiter);
        
        // Create sample jobs
        Job javaDeveloper = new Job(getNextJobId(), "Senior Java Developer", 
            "We are looking for an experienced Java developer with Spring Boot knowledge. " +
            "Responsibilities include developing backend services, working with databases, and collaborating with the team.", techCorp);
        javaDeveloper.addRequiredSkill(javaSkill);
        javaDeveloper.addRequiredSkill(springSkill);
        javaDeveloper.addRequiredSkill(sqlSkill);
        
        Job dataScientist = new Job(getNextJobId(), "Data Scientist", 
            "Join our data science team to work on machine learning projects. " +
            "Experience with Python, statistical analysis, and data visualization required.", dataSoft);
        dataScientist.addRequiredSkill(pythonSkill);
        dataScientist.addRequiredSkill(awsSkill);
        
        Job frontendDeveloper = new Job(getNextJobId(), "Frontend Developer", 
            "Create beautiful and responsive web applications using modern JavaScript frameworks. " +
            "Experience with React and modern CSS required.", webSolutions);
        frontendDeveloper.addRequiredSkill(javascriptSkill);
        frontendDeveloper.addRequiredSkill(reactSkill);
        
        Job devopsEngineer = new Job(getNextJobId(), "DevOps Engineer", 
            "Manage cloud infrastructure and implement CI/CD pipelines. " +
            "Experience with AWS, Docker, and automation tools required.", cloudTech);
        devopsEngineer.addRequiredSkill(awsSkill);
        devopsEngineer.addRequiredSkill(dockerSkill);
        devopsEngineer.addRequiredSkill(pythonSkill);
        
        Job fullstackDeveloper = new Job(getNextJobId(), "Full Stack Developer", 
            "Develop both frontend and backend applications. " +
            "Knowledge of Java, JavaScript, and databases required.", techCorp);
        fullstackDeveloper.addRequiredSkill(javaSkill);
        fullstackDeveloper.addRequiredSkill(javascriptSkill);
        fullstackDeveloper.addRequiredSkill(sqlSkill);
        
        jobs.put(javaDeveloper.getId(), javaDeveloper);
        jobs.put(dataScientist.getId(), dataScientist);
        jobs.put(frontendDeveloper.getId(), frontendDeveloper);
        jobs.put(devopsEngineer.getId(), devopsEngineer);
        jobs.put(fullstackDeveloper.getId(), fullstackDeveloper);
        
        // Add jobs to companies
        techCorp.addJob(javaDeveloper);
        techCorp.addJob(fullstackDeveloper);
        dataSoft.addJob(dataScientist);
        webSolutions.addJob(frontendDeveloper);
        cloudTech.addJob(devopsEngineer);
        
        // Create sample applications
        Application app1 = new Application(getNextApplicationId(), johnDoe, javaDeveloper);
        Application app2 = new Application(getNextApplicationId(), janeSmith, dataScientist);
        Application app3 = new Application(getNextApplicationId(), mikeJohnson, frontendDeveloper);
        Application app4 = new Application(getNextApplicationId(), johnDoe, fullstackDeveloper);
        
        applications.put(app1.getId(), app1);
        applications.put(app2.getId(), app2);
        applications.put(app3.getId(), app3);
        applications.put(app4.getId(), app4);
        
        // Add applications to job seekers and jobs
        johnDoe.addApplication(app1);
        johnDoe.addApplication(app4);
        janeSmith.addApplication(app2);
        mikeJohnson.addApplication(app3);
        
        javaDeveloper.addApplication(app1);
        dataScientist.addApplication(app2);
        frontendDeveloper.addApplication(app3);
        fullstackDeveloper.addApplication(app4);
        
        // Create sample posts
        Post post1 = new Post(getNextPostId(), "We're hiring! Looking for talented Java developers to join our team.", sarahRecruiter);
        Post post2 = new Post(getNextPostId(), "Exciting opportunity for data scientists! Join our AI/ML team.", tomRecruiter);
        Post post3 = new Post(getNextPostId(), "Frontend developers wanted! Work on cutting-edge web applications.", lisaRecruiter);
        
        posts.put(post1.getId(), post1);
        posts.put(post2.getId(), post2);
        posts.put(post3.getId(), post3);
        
        // Add posts to recruiters
        sarahRecruiter.addPost(post1);
        tomRecruiter.addPost(post2);
        lisaRecruiter.addPost(post3);
        
        // Create sample feedback
        Feedback feedback1 = new Feedback(getNextFeedbackId(), "Great company to work for! Excellent work environment.", 5, johnDoe, techCorp);
        Feedback feedback2 = new Feedback(getNextFeedbackId(), "Interesting projects and good team collaboration.", 4, janeSmith, dataSoft);
        
        feedbacks.put(feedback1.getId(), feedback1);
        feedbacks.put(feedback2.getId(), feedback2);
        
        // Save the sample data

        System.out.println("Sample data initialized successfully!");
    }


    // ID generators
    public long getNextJobSeekerId() { return nextJobSeekerId++; }
    public long getNextRecruiterId() { return nextRecruiterId++; }
    public long getNextJobId() { return nextJobId++; }
    public long getNextApplicationId() { return nextApplicationId++; }
    public long getNextCompanyId() { return nextCompanyId++; }
    public long getNextSkillId() { return nextSkillId++; }
    public long getNextPostId() { return nextPostId++; }
    public long getNextFeedbackId() { return nextFeedbackId++; }

    // Getters for collections
    public Map<Long, JobSeeker> getJobSeekers() { return jobSeekers; }
    public Map<Long, Recruiter> getRecruiters() { return recruiters; }
    public Map<Long, Job> getJobs() { return jobs; }
    public Map<Long, Application> getApplications() { return applications; }
    public Map<Long, Company> getCompanies() { return companies; }
    public Map<Long, Skill> getSkills() { return skills; }
    public Map<Long, Post> getPosts() { return posts; }
    public Map<Long, Feedback> getFeedbacks() { return feedbacks; }

    // CRUD operations
    public void addJobSeeker(JobSeeker jobSeeker) {
        jobSeekers.put(jobSeeker.getId(), jobSeeker);
    }

    public void addRecruiter(Recruiter recruiter) {
        recruiters.put(recruiter.getId(), recruiter);
    }

    public void addJob(Job job) {
        jobs.put(job.getId(), job);
    }

    public void addApplication(Application application) {
        applications.put(application.getId(), application);
    }

    public void addCompany(Company company) {
        companies.put(company.getId(), company);
    }

    public void addSkill(Skill skill) {
        skills.put(skill.getId(), skill);
    }

    public void addPost(Post post) {
        posts.put(post.getId(), post);

    }

    public void addFeedback(Feedback feedback) {
        feedbacks.put(feedback.getId(), feedback);

    }

    // Method to reset database with sample data
    public void resetWithSampleData() {
        System.out.println("Resetting database with sample data...");
        initializeCollections();
        resetIdCounters();
        initializeSampleData();
        System.out.println("Database reset completed!");
    }

    private void resetIdCounters() {
        nextJobSeekerId = 1;
        nextRecruiterId = 1;
        nextJobId = 1;
        nextApplicationId = 1;
        nextCompanyId = 1;
        nextSkillId = 1;
        nextPostId = 1;
        nextFeedbackId = 1;
    }
} 