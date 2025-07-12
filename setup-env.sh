#!/bin/bash

# Environment Setup Script for Job Portal
# This script helps developers set up their local environment

echo "🚀 Setting up Job Portal Development Environment..."

# Create logs directory if it doesn't exist
if [ ! -d "logs" ]; then
    echo "📁 Creating logs directory..."
    mkdir -p logs
fi

# Check if database.properties exists
if [ ! -f "config/database.properties" ]; then
    echo "📝 Creating database.properties from template..."
    cp config/database.properties.template config/database.properties
    echo "⚠️  Please update config/database.properties with your database credentials!"
else
    echo "✅ database.properties already exists"
fi

# Check if application.properties exists
if [ ! -f "config/application.properties" ]; then
    echo "📝 Creating application.properties from template..."
    cp config/application.properties.template config/application.properties
    echo "⚠️  Please update config/application.properties with your settings!"
else
    echo "✅ application.properties already exists"
fi

# Create .env file for environment variables (optional)
if [ ! -f ".env" ]; then
    echo "📝 Creating .env file..."
    cat > .env << EOF
# Environment Variables
# Copy this file to .env.local and update with your values

# Database
DB_HOST=localhost
DB_PORT=3306
DB_NAME=Jobportal
DB_USER=your_username
DB_PASSWORD=your_password

# Application
APP_ENV=development
APP_DEBUG=true
APP_LOG_LEVEL=DEBUG

# Security
JWT_SECRET=your_jwt_secret_here
ENCRYPTION_KEY=your_encryption_key_here
EOF
    echo "⚠️  Please update .env with your environment variables!"
else
    echo "✅ .env file already exists"
fi

echo ""
echo "🎉 Environment setup complete!"
echo ""
echo "Next steps:"
echo "1. Update config/database.properties with your database credentials"
echo "2. Update config/application.properties with your application settings"
echo "3. Update .env with your environment variables (optional)"
echo "4. Run 'mvn clean compile' to build the project"
echo ""
echo "Happy coding! 🚀" 