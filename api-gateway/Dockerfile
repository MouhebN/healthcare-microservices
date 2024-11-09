# Dockerfile
FROM openjdk:21-jdk-slim
EXPOSE 8080
ADD target/Api_Gateway_server-0.0.1-SNAPSHOT.jar api_Gateway_server.jar
ENTRYPOINT ["java", "-jar", "api_Gateway_server.jar"]
