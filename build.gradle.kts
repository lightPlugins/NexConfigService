plugins {
    id("maven-publish")
    id("java")
    id("io.freefair.lombok") version "8.13.1" apply false

}

group = "io.nexstudios.configservice"
version = providers.gradleProperty("serviceVersion").get()

allprojects {
    repositories {
        mavenLocal()
        mavenCentral()

        // NexServiceRegistry (JitPack)
        maven("https://jitpack.io")
    }
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "io.freefair.lombok")


    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(21))
        }
    }

    tasks.withType<JavaCompile>().configureEach {
        options.encoding = "UTF-8"
        options.release.set(21)
    }
}

tasks.named("publishToMavenLocal") {
    dependsOn(
        project(":platform").tasks.named("publishToMavenLocal")
    )
}