FROM maven:3.8.5-openjdk-17 AS build
COPY . .

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/my-order-app.jar my-order-app.jar
ENTRYPOINT ["java", "-jar","my-order-app.jar"]
EXPOSE 8080