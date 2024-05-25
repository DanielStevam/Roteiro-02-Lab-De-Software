FROM ubuntu:latest as build
# Daniel
LABEL authors="Daniel"
RUN apt-get update
RUN apt-get install openjdk-17-jdk -y

COPY . .

RUN apt-get install maven -y
RUN mvn clean install
RUN mvn test

FROM openjdk:17-jdk-slim

EXPOSE 8080
COPY --from=build target/*.jar app.jar

ENTRYPOINT ["java", "-jar","app.jar"]
