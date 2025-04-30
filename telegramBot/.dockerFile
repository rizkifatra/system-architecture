FROM maven:3.8.4-openjdk-17-slim AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package

FROM openjdk:17-jdk-slim

WORKDIR /app

# Copy the wait-for-it.sh script to the image
COPY wait-for-it.sh /app/wait-for-it.sh
RUN chmod +x /app/wait-for-it.sh

# Copy the built jar file to the image
COPY target/telegramBot-1.0-SNAPSHOT.jar /app/telegramBot-1.0-SNAPSHOT.jar

EXPOSE 8090

# Use wait-for-it.sh to wait for Cassandra before starting the bot
CMD ["/app/wait-for-it.sh", "cassandra:9042", "--", "java", "-jar", "telegramBot-1.0-SNAPSHOT.jar"]
