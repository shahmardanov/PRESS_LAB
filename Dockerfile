# Aşama 1: Uygulamayı derleyin
FROM maven:3.8.8-eclipse-temurin-17 AS build
WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn clean package -DskipTests

# Aşama 2: Uygulamayı çalıştırın
FROM openjdk:17-jdk-slim
WORKDIR /app

COPY --from=build /app/target/demo-0.0.1-SNAPSHOT.jar demo.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "demo.jar"]
