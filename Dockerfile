FROM openjdk:18-jdk-alpine3.15
# WORKDIR /app
# COPY mvnw pom.xml ./
COPY target/myKanBan-0.0.1-SNAPSHOT.jar app.jar
# RUN ./mvnw dependency:go-offline
COPY src ./src
ENTRYPOINT ["java", "-jar", "app.jar"]