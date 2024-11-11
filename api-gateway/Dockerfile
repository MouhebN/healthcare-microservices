
FROM openjdk:21-jdk-slim
EXPOSE 8090
ADD target/Api_Gateway_server-0.0.1-SNAPSHOT.jar api-gateway.jar
ENTRYPOINT ["java", "-jar", "api-gateway.jar"]