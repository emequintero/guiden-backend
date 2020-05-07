FROM openjdk:8-jdk-alpine
COPY target/guiden.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 9090