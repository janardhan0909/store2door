# Pull base image 
FROM openjdk:8-jdk-alpine

# Maintainer 
MAINTAINER "mahesh.vendodu@mail.com" 

# Copy to images tomcat path 
COPY target/Store2Door-0.0.1-SNAPSHOT.jar /Store2Door.jar

EXPOSE 80
CMD ["java", "-jar", "-Dspring.profiles.active=uat", "/Store2Door.jar"]
