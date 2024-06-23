# Maven image kullanarak yeni bir stage oluşturuyoruz
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY . /app
RUN mvn clean install -DskipTests

# İkinci stage: Spring Boot uygulaması için bir runtime ortamı oluşturuyoruz
FROM openjdk:17-alpine
WORKDIR /app
COPY --from=build /app/target/Press_Lab-0.0.1-SNAPSHOT.jar /app/demo.jar
EXPOSE 8585
ENTRYPOINT ["java", "-jar", "/app/demo.jar"]