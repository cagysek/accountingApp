FROM maven:3.6-jdk-8

COPY ./app /build
WORKDIR /build
RUN mvn -f /build/pom.xml clean install

FROM tomcat:8



COPY --from=0 /build/target/*.war /usr/local/tomcat/webapps/ROOT.war
