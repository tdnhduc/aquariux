# wrtie dockerfile for java 17 springbot3.0.0

# Pull base image
FROM openjdk:17-jdk-slim

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8080 available to the world outside this container
EXPOSE 8080:8080

# The application's jar file
ARG JAR_FILE=build/libs/trading-svc-1.0.0.jar

# Add the application's jar to the container
ADD ${JAR_FILE} spring-boot-docker.jar

# Run the jar file
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/spring-boot-docker.jar"]