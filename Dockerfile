FROM openjdk:14-alpine

VOLUME /tmp
ARG JAR_FILE
COPY target/conta-bancaria.jar app.jar


ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
