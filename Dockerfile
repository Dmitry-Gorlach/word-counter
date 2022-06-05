FROM openjdk:11-jdk-slim
COPY target/word-counter-0.0.1-SNAPSHOT.jar word-counter.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Xdebug", "-Xrunjdwp:transport=dt_socket,address=*:8000,server=y,suspend=n", \
"-jar","word-counter.jar", "-Djava.awt.headless=true"]
