FROM openjdk:17
EXPOSE 8080
ADD target/tech-challenge-pos-tech.jar tech-challenge-pos-tech.jar
ENV JAVA_APP_ARGS="--spring.config.location=/src/main/resources/application.properties"
ENTRYPOINT ["java","-jar","tech-challenge-pos-tech.jar", "$JAVA_APP_ARGS"]