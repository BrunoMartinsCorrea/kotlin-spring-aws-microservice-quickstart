val springBootVersion = findProperty("spring.boot.version")
val jwtVersion = findProperty("jwt.version")
val javaServletVersion = findProperty("java.servlet.version")
val springDocVersion = findProperty("springdoc.version")

dependencies {
    implementation(project(":entity"))
    implementation(project(":usecase"))

    implementation("org.springframework:spring-web:$springBootVersion")
    implementation("org.springframework.boot:spring-boot-starter-actuator:$springBootVersion")
    implementation("org.springframework.boot:spring-boot-starter-security:$springBootVersion")
    implementation("io.jsonwebtoken:jjwt:$jwtVersion")
    implementation("javax.servlet:javax.servlet-api:$javaServletVersion")
    implementation("org.springdoc:springdoc-openapi-kotlin:$springDocVersion")
    implementation("org.springdoc:springdoc-openapi-ui:$springDocVersion")
    implementation("org.springdoc:springdoc-openapi-security:$springDocVersion")
}
