FROM openjdk:8-jdk-alpine
RUN mkdir "QTech"
COPY target/*.jar /QTech
COPY production-jdbc.properties /QTech
COPY Blank_A4.jrxml /QTech
WORKDIR /QTech
RUN mkdir "print"
RUN apk add ttf-dejavu
EXPOSE 8080 
ENTRYPOINT ["java","-jar","QTech-0.0.1-SNAPSHOT.jar","--spring.config.additional-location=production-jdbc.properties"]