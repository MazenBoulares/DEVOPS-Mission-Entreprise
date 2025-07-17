FROM eclipse-temurin:17-jdk-alpine
ARG JAR_FILE=target/Foyer-1.4.0-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
