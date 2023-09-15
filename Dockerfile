FROM amazoncorretto:11.0.20-alpine3.18

COPY  target/*.jar apipagos.jar

ENTRYPOINT ["java","-jar","apipagos.jar"]