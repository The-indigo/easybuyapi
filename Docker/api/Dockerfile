FROM openjdk:17-slim AS BUILD_IMAGE
RUN apt update && apt install maven -y && apt install git -y
RUN git clone https://github.com/The-indigo/easybuyapi
RUN cd easybuyapi && git checkout demo-branch && mvn install

FROM tomcat:10-jdk17-openjdk
RUN rm -rf /usr/local/tomcat/webapps/*
COPY --from=BUILD_IMAGE easybuyapi/target/easybuyapi-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war
EXPOSE 8080

CMD ["catalina.sh", "run"]
