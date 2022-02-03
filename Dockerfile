FROM openjdk:17-oracle
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} CyberDoneCloudGatewayMicroservice.jar
EXPOSE 9090
ENTRYPOINT ["java","-jar","/CyberDoneCloudGatewayMicroservice.jar"]