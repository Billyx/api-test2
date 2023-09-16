
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY ./pom.xml ./pom.xml
COPY ./src ./src
RUN mvn clean install

FROM openjdk:17
COPY --from=build /app/target/api-0.0.1-SNAPSHOT.jar /app.jar

CMD ["java", "-jar", "/app.jar"]