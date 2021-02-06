FROM openjdk:11-jdk-slim

WORKDIR /tmp

COPY . .

RUN ./gradlew clean ktlintcheck build

FROM openjdk:11-jdk-slim

EXPOSE 8080

ENV SPRING_PROFILES_ACTIVE local

WORKDIR /srv

COPY --from=0 /tmp/application/build/libs/app.jar .

CMD java -jar app.jar
