FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY . /app
RUN mvn clean package -DskipTests
CMD ["java", "-jar", "target/approve-flow-1.0.0.jar"]