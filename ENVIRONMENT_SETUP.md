# Environment Setup Guide

This guide explains how to set up your development environment for the Job Portal project safely.

## 🚀 Quick Setup

### For Linux/Mac Users:

```bash
chmod +x setup-env.sh
./setup-env.sh
```

### For Windows Users:

```cmd
setup-env.bat
```

## 📁 Environment Structure

```
config/
├── database.properties.template     # Template for database config (safe to commit)
├── application.properties.template  # Template for app config (safe to commit)
├── application-dev.properties       # Development environment config
├── application-prod.properties      # Production environment config
└── database.properties              # Actual database config (gitignored)
```

## 🔒 Security Best Practices

### What's Protected:

- ✅ `config/database.properties` - Contains real database credentials
- ✅ `config/application.properties` - Contains sensitive app settings
- ✅ `.env` - Environment variables (if used)
- ✅ `logs/` - Application logs

### What's Safe to Commit:

- ✅ `config/database.properties.template` - Template with placeholder values
- ✅ `config/application.properties.template` - Template with placeholder values
- ✅ Environment-specific configs (dev/prod) - No sensitive data

## ⚙️ Configuration Files

### Database Configuration (`database.properties`)

```properties
# Database Connection Settings
db.url=jdbc:mysql://localhost:3306/Jobportal
db.user=your_username_here
db.password=your_password_here
db.driver=com.mysql.cj.jdbc.Driver

# Connection Pool Settings
db.pool.initialSize=5
db.pool.maxSize=20
db.pool.timeout=30000
```

### Application Configuration (`application.properties`)

```properties
# Application Settings
app.name=Job Portal
app.version=1.0.0
app.environment=development

# Logging Configuration
logging.level=INFO
logging.file=logs/application.log

# Security Settings
security.session.timeout=3600
security.password.minLength=8
```

## 🌍 Environment-Specific Configurations

### Development Environment (`application-dev.properties`)

- Debug mode enabled
- Verbose logging
- Relaxed security settings
- All features enabled

### Production Environment (`application-prod.properties`)

- Debug mode disabled
- Minimal logging
- Strict security settings
- Selective features enabled
- Monitoring enabled

## 🔧 Manual Setup Steps

If you prefer to set up manually:

1. **Create logs directory:**

   ```bash
   mkdir -p logs
   ```

2. **Copy database template:**

   ```bash
   cp config/database.properties.template config/database.properties
   ```

3. **Update database credentials:**
   Edit `config/database.properties` with your actual database details

4. **Copy application template:**

   ```bash
   cp config/application.properties.template config/application.properties
   ```

5. **Update application settings:**
   Edit `config/application.properties` with your preferences

## 🚨 Important Notes

### Before Committing:

- ✅ Never commit `database.properties` with real credentials
- ✅ Never commit `.env` files with sensitive data
- ✅ Always use templates for configuration examples
- ✅ Test your configuration locally before pushing

### For Team Development:

- ✅ Share the setup scripts with your team
- ✅ Document any environment-specific requirements
- ✅ Use consistent naming conventions
- ✅ Keep templates updated with new configuration options

## 🔍 Troubleshooting

### Common Issues:

1. **Database connection fails:**

   - Check if MySQL is running
   - Verify credentials in `database.properties`
   - Ensure database `Jobportal` exists

2. **Configuration not loading:**

   - Verify file paths are correct
   - Check file permissions
   - Ensure no syntax errors in properties files

3. **Git ignoring wrong files:**
   - Check `.gitignore` rules
   - Use `git status` to verify what's tracked
   - Use `git check-ignore` to test ignore rules

## 📚 Additional Resources

- [Maven Configuration Guide](https://maven.apache.org/guides/)
- [MySQL Connection Properties](https://dev.mysql.com/doc/connector-j/8.0/en/connector-j-reference-configuration-properties.html)
- [Java Properties File Format](https://docs.oracle.com/javase/8/docs/api/java/util/Properties.html)

---

**Remember:** Always keep your credentials secure and never commit sensitive information to version control! 🔐
