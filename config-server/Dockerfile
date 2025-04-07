FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /app
COPY . .
RUN mvn clean package -pl config-server -am -DskipTests

FROM eclipse-temurin:21-jre-jammy

COPY --from=build /app/config-server/target/*.jar /app/app.jar

ENTRYPOINT ["java", "-jar", "/app/app.jar"]]