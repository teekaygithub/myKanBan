FROM maven:3.8.4-jdk-11-slim AS build
WORKDIR /usr/app
COPY pom.xml ./
RUN ["mvn", "dependency:go-offline"]

COPY src ./src
ENV MKB_HOST=${MKB_HOST}
ENV SQK_TK_USER=${SQL_TK_USER}
ENV SQL_TK_PW=${SQL_TK_PW}
RUN ["mvn", "-f", "./pom.xml", "package", "-Dmaven.test.skip=true"]

FROM openjdk:18-jdk-alpine3.15
COPY --from=build /usr/app/target/myKanBan-0.0.1-SNAPSHOT.jar /usr/local/lib/app.jar
# COPY src ./src
# ENTRYPOINT ["java", "-jar", "app.jar"]
ENTRYPOINT ["java", "-jar", "/usr/local/lib/app.jar"]