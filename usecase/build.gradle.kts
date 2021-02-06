val springContextVersion = findProperty("spring.context.version")

dependencies {
    implementation(project(":entity"))

    implementation("org.springframework:spring-context:$springContextVersion")
}
