FROM openjdk:8
EXPOSE 8081
ADD target/API_Gateway_Server-0.0.1-SNAPSHOT.jar api-gateway.jar
ENTRYPOINT ["java", "-jar", "api-gateway.jar"]