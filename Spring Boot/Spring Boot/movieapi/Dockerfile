# Build stage
FROM maven:3.8.4-openjdk-17-slim AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package

# Run stage
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
COPY src/main/resources/data.sql /app/data.sql

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]

# File kita
# FROM openjdk:17-jdk-alpine

# WORKDIR /app

# COPY target/movieapi-0.0.1-SNAPSHOT.jar /app/movieapi-0.0.1-SNAPSHOT.jar

# EXPOSE 8080

# CMD ["java", "-jar", "movieapi-0.0.1-SNAPSHOT.jar"]