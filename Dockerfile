# Use a valid OpenJDK 17 slim image
FROM openjdk:17-jdk-slim-bullseye

# Set working directory
WORKDIR /app

# Copy the Maven-built JAR
COPY target/*.jar app.jar

# Expose port (adjust if needed)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
