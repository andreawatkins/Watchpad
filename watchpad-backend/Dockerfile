FROM maven:3.8.5-eclipse-temurin-17-alpine as build
WORKDIR /app

COPY ./pom.xml ./
COPY ./src ./src
RUN mvn clean package -Dmaven.test.skip=true

FROM openjdk:17
WORKDIR /app
COPY --from=build ./app/target/watchpad-backend-0.0.1-SNAPSHOT.jar ./target/watchpad-backend-0.0.1-SNAPSHOT.jar
EXPOSE 8080

CMD java -jar ./target/watchpad-backend-0.0.1-SNAPSHOT.jar