# Use an official OpenJDK runtime as a parent image
FROM openjdk:21-jdk-slim

# Expose the port your application will run on
EXPOSE 8087

# Copy the generated JAR file to the container
ADD target/Patient-0.0.1-SNAPSHOT.jar patient-service.jar

# Run the JAR file
ENTRYPOINT ["java", "-jar", "patient-service.jar"]
