FROM eclipse-temurin:17-jdk-alpine
EXPOSE 8090
ADD target/application-0.0.1-SNAPSHOT.jar /app/customer-service.jar
ENTRYPOINT ["java", "-jar","/app/customer-service.jar"]