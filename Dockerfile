# Use an official OpenJDK runtime as a parent image
FROM openjdk:21-jdk-slim AS builder

# Set the working directory in the container
WORKDIR /app

# Copy the Maven wrapper files and project files
COPY .mvn .mvn
COPY mvnw .

# Copy the project files into the container
COPY pom.xml .

# Grant maven wrapper executable privilages
RUN chmod +x mvnw

# Download and install any dependencies specified in pom.xml
RUN ./mvnw dependency:go-offline

# Copy the application source code into the container
COPY src src

# Build the application
RUN ./mvnw package -Dmaven.test.skip


FROM openjdk:21-jdk-slim

# Copy build artifact
COPY --from=builder /app/target/*.jar app.jar

# Specify the command to run on container start
ENTRYPOINT ["java", "-jar", "/app.jar"]
