FROM openjdk:21-jdk-slim
EXPOSE 8088
ADD target/micro-rdv-0.0.1-SNAPSHOT.jar rdv-service.jar
ENTRYPOINT ["java", "-jar", "rdv-service.jar"]
