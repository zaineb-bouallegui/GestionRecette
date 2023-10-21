FROM openjdk:8
EXPOSE 8089
ADD target/Profile-0.0.1-SNAPSHOT.jar profile.jar
ENTRYPOINT ["java","-jar","profile.jar"]