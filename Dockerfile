# Use OpenJDK 17 as the base image
FROM eclipse-temurin:17-jdk

# Set working directory
WORKDIR /app

# Copy Maven wrapper & dependencies first to cache them
COPY mvnw pom.xml ./
COPY .mvn .mvn
RUN chmod +x mvnw && ./mvnw dependency:go-offline

# Copy the entire project
COPY . .

# Build the project (skip tests for faster build)
RUN ./mvnw clean package -DskipTests

# Expose the application port
EXPOSE 8080

# Run the application using the built JAR
CMD ["java", "-jar", "target/*.jar"]
