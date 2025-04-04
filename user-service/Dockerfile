FROM bellsoft/liberica-openjdk-debian:23.0.1

WORKDIR /application
COPY . .
RUN chmod +x mvnw && ./mvnw clean install -Dmaven.test.skip && \
    mv /application/target/*.jar /app.jar && \
    rm -rf /application

ENTRYPOINT ["java", "-jar", "/app.jar"]