FROM maven:3.8.4-openjdk-17-slim AS build

WORKDIR /app

# Copy the POM file
COPY pom.xml .

# Download all required dependencies into one layer
RUN mvn dependency:go-offline -B

# Copy your other files
COPY src ./src

# Build the application
RUN mvn package -DskipTests

# Stage 2: Create the runtime image
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copy the built artifact from the build stage
COPY --from=build /app/target/*.jar app.jar

# Create a non-root user
RUN useradd -m myuser
USER myuser

# Set the startup command to execute the jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]