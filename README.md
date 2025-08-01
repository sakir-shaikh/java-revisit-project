# Job Portal System

A simple job portal application built with **Plain Java** and **JSON-based database** for managing job seekers, recruiters, jobs, and applications.

## 🏗️ Project Structure

```
src/
├── config/                # App-level config (DB, constants, etc.)
│   └── DatabaseConfig.java
├── model/                 # Data models / entities
│   ├── User.java
│   ├── JobSeeker.java
│   ├── Recruiter.java
│   ├── Job.java
│   ├── Application.java
│   ├── Profile.java
│   ├── Skill.java
│   ├── Company.java
│   ├── Post.java
│   └── Feedback.java
├── service/               # Business logic layer
│   ├── UserService.java
│   ├── JobService.java
│   ├── ApplicationService.java
│   └── AuthService.java
├── dao/                   # Data Access Layer (CRUD logic)
│   ├── UserDAO.java
│   ├── JobDAO.java
│   ├── ApplicationDAO.java
│   └── Database.java      # JSON database implementation
├── util/                  # Utilities (validators, helpers)
│   ├── Validator.java
│   └── ConsoleColors.java
├── ui/                    # CLI user interface
│   ├── MainMenu.java
│   ├── JobSeekerMenu.java
│   └── RecruiterMenu.java
└── Main.java              # Entry point
```

## 🚀 Features

### For Job Seekers:

- ✅ User registration and login
- ✅ Browse all available jobs
- ✅ Search jobs by title or skills
- ✅ Apply for jobs
- ✅ View application history
- ✅ Withdraw applications

### For Recruiters:

- ✅ User registration and login
- ✅ Create new job postings
- ✅ View all applications
- ✅ Update application status (Accept/Reject)
- ✅ Delete job postings

### System Features:

- ✅ JSON-based data persistence
- ✅ Input validation
- ✅ Colored console output
- ✅ Error handling
- ✅ Layered architecture

## 🛠️ Technology Stack

- **Language**: Java (Plain Java, no frameworks)
- **Database**: JSON file (`src/database.json`)
- **Architecture**: Layered (Model-Service-DAO-UI)
- **UI**: Command Line Interface (CLI)

## 📋 Prerequisites

- Java 8 or higher
- Any Java IDE (IntelliJ IDEA, Eclipse, VS Code)

## 🏃‍♂️ How to Run

1. **Clone or download the project**
2. **Open in your Java IDE**
3. **Run the main class**: `src/Main.java`
4. **Follow the CLI prompts**

### Alternative: Command Line

```bash
# Compile
javac -d out src/**/*.java

# Run
java -cp out Main
```

## 🎯 Usage Examples

### Starting the Application

```
=== WELCOME TO JOB PORTAL SYSTEM ===
A simple job portal built with plain Java and JSON database

Database initialized with empty data
Initializing database with sample data...
Sample data initialized successfully!

=== MAIN MENU ===
1. Login
2. Register
3. Exit
```

### Sample Data Available

The system comes pre-loaded with sample data:

**👥 Sample Users:**

- **Job Seekers:** John Doe (john@example.com), Jane Smith (jane@example.com), Mike Johnson (mike@example.com)
- **Recruiters:** Sarah Wilson (sarah@techcorp.com), Tom Brown (tom@datasoft.com), Lisa Davis (lisa@websolutions.com)
- **Password for all users:** `password123`

**🏢 Sample Companies:**

- TechCorp - Software development
- DataSoft - Data analytics and ML
- WebSolutions - Web development
- CloudTech - Cloud infrastructure

**💼 Sample Jobs:**

- Senior Java Developer (TechCorp)
- Data Scientist (DataSoft)
- Frontend Developer (WebSolutions)
- DevOps Engineer (CloudTech)
- Full Stack Developer (TechCorp)

**🔧 Sample Skills:**

- Java, Python, JavaScript, SQL, React, Spring Boot, AWS, Docker

**📝 Sample Applications:**

- John Doe → Senior Java Developer
- Jane Smith → Data Scientist
- Mike Johnson → Frontend Developer
- John Doe → Full Stack Developer

### Job Seeker Registration

```
=== JOB SEEKER REGISTRATION ===
Enter name: John Doe
Enter email: john@example.com
Enter password: password123
Registration successful! Welcome, John Doe!
```

### Job Application

```
=== APPLY FOR JOB ===
Enter job ID to apply: 1
Application submitted successfully! Application ID: 1
```

## 🏗️ Architecture Overview

### Layers:

1. **Model Layer**: Data entities (User, Job, Application, etc.)
2. **DAO Layer**: Data access operations with JSON persistence
3. **Service Layer**: Business logic and validation
4. **UI Layer**: Command-line interface

### Key Classes:

- `Database.java`: Singleton JSON database manager
- `UserService.java`: User management and authentication
- `JobService.java`: Job-related operations
- `ApplicationService.java`: Application management
- `Validator.java`: Input validation utilities
- `ConsoleColors.java`: Colored console output

## 📊 Data Model

The system uses 9 main entities:

- **User** (abstract base class)
- **JobSeeker** (extends User)
- **Recruiter** (extends User)
- **Job** (job postings)
- **Application** (job applications)
- **Company** (employer information)
- **Skill** (job skills)
- **Profile** (job seeker profiles)
- **Post** (recruiter posts)
- **Feedback** (company feedback)

## 🔧 Configuration

The system uses a simple JSON file (`src/database.json`) for data persistence. The database is automatically created when the application starts.

## 🧪 Testing

Run the test file to verify all components:

```bash
java -cp out JobPortalTest
```

## 📝 Notes

- This is a **plain Java implementation** without external dependencies
- Data is stored in a simple JSON file (not a real database)
- The system is designed for learning and demonstration purposes
- All validation and business logic is implemented in Java
