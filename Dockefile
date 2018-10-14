FROM maven:3.3-jdk-8-onbuild as build

FROM tomcat:8.0.20-jre8
EXPOSE 8080
COPY --from=build /usr/src/app/target/video-metadata-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/api.war