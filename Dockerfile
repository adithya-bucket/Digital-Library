# Use OpenJDK 17 as the base image
FROM eclipse-temurin:17-jdk

# Set working directory
WORKDIR /app

# Copy the entire project
COPY . .

# Install Maven (if needed)
RUN apt update && apt install -y maven

# Build the project (skip tests to speed up deployment)
RUN mvn clean package -DskipTests

# Expose the default Spring Boot port
EXPOSE 8080

# Run the application using the built JAR
CMD ["java", "-jar", "target/*.jar"]
