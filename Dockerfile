# Build
# Use OpenJDK 21 as the base image
FROM maven:3.9.9-amazoncorretto-21-al2023

COPY pom.xml /
RUN mvn dependency:go-offline
RUN mvn verify clean
## build after dependencies are down so it wont redownload unless the POM changes
COPY . .
RUN mvn clean install

# Run
FROM amazoncorretto:21-al2023-jdk

# Set the working directory inside the container
WORKDIR /app

# Copy the built JAR file from the Maven target directory to the container
COPY target/*.jar app.jar

# Expose the port your Spring Boot app runs on (default 8080)
EXPOSE 8080

# Command to run the Spring Boot application
CMD ["java", "-jar", "app.jar"]