FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD ./target/salesforce.sample.ecommerce.service.order.jar app.jar
RUN sh -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]