val springContextVersion = findProperty("spring.context.version")
val awsSdkVersion = findProperty("aws.sdk.version")

dependencies {
    implementation(project(":entity"))
    implementation(project(":usecase"))

    implementation("org.springframework:spring-context:$springContextVersion")
    implementation("com.amazonaws:aws-java-sdk:$awsSdkVersion")
}
