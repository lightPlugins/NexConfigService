plugins {
    id("java-library")
    id("maven-publish")
}

group = "io.nexstudios.configservice"
version = providers.gradleProperty("serviceVersion").get()

dependencies {
    api("com.github.lightplugins:NexServiceRegistry:${providers.gradleProperty("registryVersion").get()}")
    api("org.spongepowered:configurate-yaml:4.2.0")

    testImplementation(platform("org.junit:junit-bom:5.10.2"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}


publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
        }
    }
}