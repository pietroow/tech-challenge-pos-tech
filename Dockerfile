FROM openjdk:17
EXPOSE 8080
ADD out/artifacts/tech_challenge_pos_tech_jar/tech-challenge-pos-tech.jar tech-challenge-pos-tech.jar
ENV JAVA_APP_ARGS="--spring.config.location=/src/main/resources/application.properties"
ENTRYPOINT ["java","-jar","tech-challenge-pos-tech.jar", "$JAVA_APP_ARGS"]

FROM postgres:latest
ENV POSTGRES_DB=restaurante
ENV POSTGRES_USER=postgres
ENV POSTGRES_PASSWORD=password
EXPOSE 5432

#FROM ubuntu:latest
#ARG DEBIAN_FRONTEND=noninteractive
#RUN apt-get update -y
#RUN apt-get install -y openjdk-11-jre postgresql
#EXPOSE 8080
#
#ADD  .mvn/wrapper/maven-wrapper.jar tech-challenge-pos-tech-1.jar
##COPY .mvn/wrapper/maven-wrapper.jar tech-challenge-pos-tech-1.jar
#
#RUN service postgresql start
#RUN su -c "psql -U postgres -c 'CREATE DATABASE tech-challenge-pos-tech-1;'" postgres
#
## Defina a variável de ambiente para a aplicação Java
#
## Inicialize a aplicação Java
#CMD ["java", "-jar", ".mvn/wrapper/maven-wrapper.jar", "$JAVA_APP_ARGS"]

#FROM postgres
#ENV POSTGRES_PASSWORD 123456
#ENV POSTGRES_DB tech-challenge-pos-tech-1
#ADD /docker-entrypoint-initdb.d/ tech-challenge-pos-tech-1.sql
#EXPOSE 5432
#CMD ["postgres"]
#
#FROM postgres:latest
#ENV POSTGRES_DB=tech-challenge-pos-tech-1
#ENV POSTGRES_USER=postgres
#ENV POSTGRES_PASSWORD=123456
#EXPOSE 5432