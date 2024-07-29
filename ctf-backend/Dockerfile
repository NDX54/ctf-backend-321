FROM eclipse-temurin:21
EXPOSE 8085
WORKDIR /ctf-backend
ARG JAR=ctf-backend-0.0.1-SNAPSHOT.jar

COPY /target/$JAR /ctf-backend.jar
CMD ["java", "-jar", "/ctf-backend.jar"]