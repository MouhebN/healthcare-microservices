# Use an official OpenJDK runtime as a parent image
FROM openjdk:21-jdk-slim

# Expose the port your application will run on
EXPOSE 8089

# Copy the generated JAR file to the container
ADD target/facturation-0.0.1-SNAPSHOT.jar facture-service.jar

# Run the JAR file
ENTRYPOINT ["java", "-jar", "facture-service.jar"]
