# Dockerfile
FROM openjdk:21-jdk-slim
EXPOSE 8180
ADD target/medecin-0.0.1-SNAPSHOT.jar medecin-micro-service.jar
ENTRYPOINT ["java", "-jar", "medecin.jar"]
