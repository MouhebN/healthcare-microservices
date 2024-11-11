# Use an official OpenJDK runtime as a parent image
FROM openjdk:21-jdk-slim

# Expose the port that your Spring Boot application will run on
EXPOSE 8186

# Add the application JAR file to the container
ADD target/fiche-ms-0.0.1-SNAPSHOT.jar fiche-service.jar

# Run the JAR file
ENTRYPOINT ["java", "-jar", "fiche-service.jar"]
