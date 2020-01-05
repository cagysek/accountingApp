FROM adoptopenjdk:11-jre-hotspot
RUN mkdir /opt/app
COPY app/target/accountingApp-0.0.1-SNAPSHOT.war /opt/app
CMD ["java", "-jar", "/opt/app/accountingApp-0.0.1-SNAPSHOT.war"]
