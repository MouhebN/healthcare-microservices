FROM openjdk:21-jdk-slim
EXPOSE 8087
ADD target/Patient-0.0.1-SNAPSHOT.jar patient-service.jar
ENTRYPOINT ["java", "-jar", "patient-service.jar"]
