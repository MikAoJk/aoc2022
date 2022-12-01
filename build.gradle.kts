import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


val jvmTargetVersion = "17"

plugins {
    kotlin("jvm") version "1.7.20"
    application
}

group = "io.github.mikaojk"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = jvmTargetVersion
}

application {
    mainClass.set("MainKt")
}