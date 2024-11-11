FROM openjdk:21-jdk-slim
EXPOSE 8186
ADD target/fiche-service-0.0.1-SNAPSHOT.jar fiche-service.jar
ENTRYPOINT ["java", "-jar", "fiche-service.jar"]
