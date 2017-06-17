From java:8
EXPOSE 8082
ADD /build/libs/pergunta2-0.1.0.jar  pergunta2-0.1.0.jar
ENTRYPOINT ["java","-jar","pergunta2-0.1.0.jar"]