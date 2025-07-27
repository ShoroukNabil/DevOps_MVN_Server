# Stage 1: Build WAR using Maven
FROM maven:3.8.6-openjdk-8-slim AS build

# Set working directory
WORKDIR /app

# Copy only pom.xml first to leverage Docker cache for dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy source code
COPY src ./src

# Build application without running tests
RUN mvn clean package -DskipTests

# Stage 2: Use lightweight JRE for runtime
FROM openjdk:8-jre-slim

# Set working directory
WORKDIR /app

# Create a volume for temporary files
VOLUME /tmp

# Copy WAR from builder stage (rename to app.war)
COPY --from=build /app/target/*.war app.war

# Expose application port
EXPOSE 8084

# Run the application
ENTRYPOINT ["java", "-jar", "app.war"]
