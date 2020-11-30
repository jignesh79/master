FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/spring-boot-rest-service-1.0-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]