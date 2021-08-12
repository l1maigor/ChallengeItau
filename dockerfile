FROM adoptopenjdk/openjdk11:alpine
RUN mkdir app
ARG JAR_FILE
ADD /target/${JAR_FILE} /app/itau-challenge-spring-docker.jar
WORKDIR /app
ENTRYPOINT java -jar itau-challenge-spring-docker.jar