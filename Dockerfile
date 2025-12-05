# Start from official OpenJDK 17
FROM openjdk:17-jdk-slim

# Set working directory inside container
WORKDIR /app

# Copy the Maven-built JAR into the container
COPY target/*.jar app.jar

# Expose the port your app runs on
EXPOSE 8080

# Run the JAR
ENTRYPOINT ["java", "-jar", "app.jar"]
