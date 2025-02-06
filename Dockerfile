FROM maven:3.8.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17
WORKDIR /app
COPY --from=build /app/target/receipt-processor.jar receipt-processor.jar
EXPOSE 8099
ENTRYPOINT ["java", "-jar", "receipt-processor.jar"]