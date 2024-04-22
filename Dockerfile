FROM maven:3.8.5-openjdk-17 AS build
COPY . .

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/myorder.jar myorder.jar
ENTRYPOINT ["java", "-jar","myorder.jar"]
EXPOSE 8080