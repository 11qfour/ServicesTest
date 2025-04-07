FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /app
COPY . .
RUN mvn clean package -pl eureka-server -am -DskipTests

FROM eclipse-temurin:21-jre-jammy

COPY --from=build /app/eureka-server/target/*.jar /app/app.jar
EXPOSE ${EUREKA_PORT:-8761}
ENTRYPOINT ["java", "-jar", "/app/app.jar"]