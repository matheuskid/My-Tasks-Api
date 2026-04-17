# --- Stage 1: Build the application ---
# Use a Maven image based on Eclipse Temurin (Standard OpenJDK build)
FROM maven:3.9-eclipse-temurin-17 AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

# --- Stage 2: Create the final image ---
# Use the JRE (Java Runtime Environment) image to keep it small
FROM eclipse-temurin:17-jre

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]