FROM openjdk:18-jdk-alpine3.15
WORKDIR /usr/app
COPY target/myKanBan-0.0.1-SNAPSHOT.jar ./app.jar
COPY src ./src
ENTRYPOINT ["java", "-jar", "app.jar"]