FROM openjdk:latest
EXPOSE 8888
ADD target/keycloak-0.0.1-SNAPSHOT.jar spring
ENTRYPOINT ["java" , "-jar", "/keycloak-0.0.1-SNAPSHOT.jar"]
