@echo off
REM Environment Setup Script for Job Portal (Windows)
REM This script helps developers set up their local environment

echo 🚀 Setting up Job Portal Development Environment...

REM Create logs directory if it doesn't exist
if not exist "logs" (
    echo 📁 Creating logs directory...
    mkdir logs
)

REM Check if database.properties exists
if not exist "config\database.properties" (
    echo 📝 Creating database.properties from template...
    copy "config\database.properties.template" "config\database.properties"
    echo ⚠️  Please update config\database.properties with your database credentials!
) else (
    echo ✅ database.properties already exists
)

REM Check if application.properties exists
if not exist "config\application.properties" (
    echo 📝 Creating application.properties from template...
    copy "config\application.properties.template" "config\application.properties"
    echo ⚠️  Please update config\application.properties with your settings!
) else (
    echo ✅ application.properties already exists
)

REM Create .env file for environment variables (optional)
if not exist ".env" (
    echo 📝 Creating .env file...
    (
        echo # Environment Variables
        echo # Copy this file to .env.local and update with your values
        echo.
        echo # Database
        echo DB_HOST=localhost
        echo DB_PORT=3306
        echo DB_NAME=Jobportal
        echo DB_USER=your_username
        echo DB_PASSWORD=your_password
        echo.
        echo # Application
        echo APP_ENV=development
        echo APP_DEBUG=true
        echo APP_LOG_LEVEL=DEBUG
        echo.
        echo # Security
        echo JWT_SECRET=your_jwt_secret_here
        echo ENCRYPTION_KEY=your_encryption_key_here
    ) > .env
    echo ⚠️  Please update .env with your environment variables!
) else (
    echo ✅ .env file already exists
)

echo.
echo 🎉 Environment setup complete!
echo.
echo Next steps:
echo 1. Update config\database.properties with your database credentials
echo 2. Update config\application.properties with your application settings
echo 3. Update .env with your environment variables (optional)
echo 4. Run 'mvn clean compile' to build the project
echo.
echo Happy coding! 🚀
pause 