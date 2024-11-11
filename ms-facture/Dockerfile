FROM openjdk:21-jdk-slim
EXPOSE 8089
ADD target/facturation-0.0.1-SNAPSHOT.jar facture-service.jar
ENTRYPOINT ["java", "-jar", "facture-service.jar"]
