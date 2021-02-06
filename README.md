# kotlin-spring-aws-microservice-quickstart

This project contains the basic architecture, technologies and design patterns to speed up your development.  

## Requirements

- [JDK 11](https://sdkman.io/jdks)
- [Kotlin 1.4.30](https://sdkman.io/sdks#kotlin)

## Technologies/Frameworks

- [Spring Boot](https://spring.io/projects/spring-boot)
- [Undertow](http://undertow.io/)
- [Swagger](https://swagger.io/)
- [Jackson](https://github.com/FasterXML/jackson)
- [Flyway](https://flywaydb.org/)
- [Postgresql](https://www.postgresql.org/)
- [H2](http://www.h2database.com/html/main.html)
- [Logback](https://logback.qos.ch/)
- [OpenTracing](https://opentracing.io/)
- [ktlint](https://ktlint.github.io/)
- [JaCoCo](https://www.eclemma.org/jacoco/)
- [Kotest](https://github.com/kotest/kotest)
- [MockK](https://mockk.io/)
- [Gradle](https://gradle.org/)

## Running

To run the application, just execute the *bootRun* task on Gradle Wrapper.

```bash
./gradlew bootRun
```

If you have *docker compose* installed in your machine, execute the following command:

```bash
docker-compose up -d
```

## Endpoints

- Base URL: http://localhost:8080/
- Swagger: http://localhost:8080/swagger-ui.html
- Info: http://localhost:8080/info
- Health: http://localhost:8080/health
