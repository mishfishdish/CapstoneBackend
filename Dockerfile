FROM openjdk:21-jdk
WORKDIR /app

# Copy all JARs and rename to app.jar
COPY build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]