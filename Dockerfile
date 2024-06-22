# Aşama 1: Uygulamayı derleyin
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests


# Aşama 2: Uygulamayı çalıştırın
FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/Press_Lab-0.0.1-SNAPSHOT.jar /app/demo.jar
EXPOSE 8585
ENTRYPOINT ["java", "-jar", "demo.jar"]
