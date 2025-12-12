FROM eclipse-temurin:17-jre-alpine

# Optional: set working directory
WORKDIR /app

# Copy the built JAR from target/ into the container
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

# Expose port if your app listens on it (optional)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java","-jar","app.jar"]
