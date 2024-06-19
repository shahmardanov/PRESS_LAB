FROM maven:3.8.8-eclipse-temurin-17 AS build
WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim
WORKDIR /app

COPY --from=build /app/target/Press_Lab-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8585

ENTRYPOINT ["java","-jar","demo.jar"]
