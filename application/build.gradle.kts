val springBootVersion = findProperty("spring.boot.version")
val springDependencyManagement = findProperty("spring.dependency.management")

plugins {
    application
    id("org.springframework.boot") version "2.3.5.RELEASE"
    id("io.spring.dependency-management") version "1.0.10.RELEASE"
    kotlin("plugin.spring")
}

dependencies {
    implementation(project(":entity"))
    implementation(project(":persistence"))
    implementation(project(":storage"))
    implementation(project(":usecase"))
    implementation(project(":web"))

    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web") {
        exclude("org.springframework.boot", "spring-boot-starter-tomcat")
        implementation("org.springframework.boot", "spring-boot-starter-undertow")
    }
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
}

application {
    mainClass.set("com.company.tribe.Application")

    applicationDefaultJvmArgs = listOf(
        "-server",
        "-XX:+UseNUMA",
        "-XX:+UseParallelGC",
        "-Duser.timezone=America/Sao_Paulo"
    )
}

tasks {
    bootJar {
        archiveBaseName.set("app")
        archiveVersion.set("")
    }
}
