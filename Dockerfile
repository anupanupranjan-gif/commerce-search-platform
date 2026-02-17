# Java 25 base image
FROM eclipse-temurin:25-jdk-alpine

WORKDIR /app

# Copy built jar
COPY target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
