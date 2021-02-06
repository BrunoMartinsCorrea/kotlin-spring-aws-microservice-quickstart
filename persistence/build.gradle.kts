val kotlinVersion = findProperty("kotlin.version")
val springBootVersion = findProperty("spring.boot.version")
val flywayVersion = findProperty("flyway.version")
val postgresqlVersion = findProperty("postgresql.version")
val h2Version = findProperty("h2.version")

buildscript {
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-noarg:1.4.10")
    }
}

plugins {
    id("org.flywaydb.flyway") version "6.5.7"
}

apply(plugin = "kotlin-jpa")

dependencies {
    implementation(project(":entity"))
    implementation(project(":usecase"))

    implementation("org.springframework.boot:spring-boot-starter-data-jpa:$springBootVersion")
    implementation("org.springframework.boot:spring-boot-starter-jdbc:$springBootVersion")
    implementation("org.flywaydb:flyway-core:$flywayVersion")
    runtimeOnly("org.postgresql:postgresql:$postgresqlVersion")
    runtimeOnly("com.h2database:h2:$h2Version")
}

flyway {
    url = System.getenv("DB_URL")
    user = System.getenv("DB_USER")
    password = System.getenv("DB_PASSWORD")
    baselineOnMigrate = true
    locations = arrayOf("classpath:db/migration")
}
