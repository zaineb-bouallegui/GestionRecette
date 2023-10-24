FROM openjdk:8
EXPOSE 8761
ADD target/eureka-0.0.1-SNAPSHOT.jar eurekaServer.jar
ENTRYPOINT ["java", "-jar", "eurekaServer.jar"]