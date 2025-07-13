# Stage 1: Build WAR using Maven
FROM maven:3.8.6-openjdk-8-slim as build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

# Stage 2: Run WAR using OpenJDK
FROM openjdk:8-jdk-slim

WORKDIR /app
VOLUME /tmp

COPY --from=build /app/target/*.war app.war

EXPOSE 8040

ENTRYPOINT ["java", "-jar", "app.war"]
