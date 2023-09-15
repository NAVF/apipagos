FROM amazoncorretto:11.0.20-alpine3.18

COPY  target/*.jar apipagos.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","apipagos.jar"]