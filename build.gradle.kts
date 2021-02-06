val javaVersion = findProperty("java.version").toString().toInt()
val kotlinVersion = findProperty("kotlin.version")
val ktlintVersion = findProperty("ktlint.version")
val ktlintPluginVersion = findProperty("ktlint.plugin.version")
val logstashEncoderVersion = findProperty("logstash.encoder.version")
val openTracingVersion = findProperty("open.tracing.version")
val springBootVersion = findProperty("spring.boot.version")
val mockKVersion = findProperty("mockk.version")

java.sourceCompatibility = JavaVersion.toVersion(javaVersion)

plugins {
    java
    kotlin("jvm") version "1.4.10"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.4.10"
    id("org.jlleitschuh.gradle.ktlint") version "9.4.1"
    id("jacoco")
}

buildscript {
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin")
        classpath("org.jetbrains.kotlin:kotlin-allopen")
    }
}

repositories {
    mavenCentral()
}

subprojects {
    group = "com.company"
    version = "2020.11.06"

    apply(plugin = "kotlin")
    apply(plugin = "kotlin-spring")
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
    apply(plugin = "jacoco")
    apply(plugin = "kotlin-allopen")

    repositories {
        mavenCentral()
    }

    dependencies {
        implementation(kotlin("stdlib-jdk8"))
        implementation(kotlin("reflect"))
        implementation("com.pinterest.ktlint:ktlint-core:$ktlintVersion")
        implementation("net.logstash.logback:logstash-logback-encoder:$logstashEncoderVersion")
        implementation("io.opentracing:opentracing-util:$openTracingVersion")

        testImplementation("org.springframework.boot:spring-boot-starter-test:$springBootVersion") {
            exclude("mockito")
        }
        testImplementation("io.mockk:mockk:$mockKVersion")
    }

    sourceSets.main {
        java.srcDirs("src/main/kotlin")
    }

    tasks {
        compileKotlin {
            kotlinOptions {
                freeCompilerArgs = listOf("-Xjvm-default=enable")
                allWarningsAsErrors = true
                jvmTarget = "$javaVersion"
            }
        }

        compileTestKotlin {
            kotlinOptions.jvmTarget = "$javaVersion"
        }

        jacocoTestReport {
            dependsOn(test)

            reports {
                xml.isEnabled = false
                csv.isEnabled = false
                html.destination = file("$buildDir/jacocoHtml")
            }
        }
    }

    ktlint {
        debug.set(false)
        outputToConsole.set(true)
        ignoreFailures.set(false)
        filter {
            exclude("**/generated/**")
            include("**/kotlin/**")
        }
    }

    jacoco {
        reportsDir = file("$buildDir/reports/jacoco")
    }
}
