import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    // Apply the Kotlin JVM plugin to add support for Kotlin.
    id("org.jetbrains.kotlin.jvm") version "1.6.0"
    // Kotlinx serialization for any data format
    kotlin("plugin.serialization") version "1.6.0"
    // Shade the plugin
    id("com.github.johnrengelman.shadow") version "7.0.0"
    // Allow publishing
    `maven-publish`

    // Apply the application plugin to add support for building a jar
    java
    // Dokka documentation w/ kotlin
    id("org.jetbrains.dokka") version "1.5.31"
}

repositories {
    // Use mavenCentral
    mavenCentral()

    maven(url = "https://jitpack.io")
    maven(url = "https://repo.spongepowered.org/maven")
    maven(url = "https://repo.velocitypowered.com/snapshots/")
}

dependencies {
    // Align versions of all Kotlin components
    compileOnly(platform("org.jetbrains.kotlin:kotlin-bom"))

    // Use the Kotlin JDK 8 standard library.
    compileOnly(kotlin("stdlib"))

    // Use the Kotlin reflect library.
    compileOnly(kotlin("reflect"))

    // Compile Minestom into project
    compileOnly("com.github.Minestom:Minestom:4ee5cbe424")

    // Add KStom
    compileOnly("com.github.Project-Cepi:KStom:f962764331")

    // Add Kepi
    compileOnly("com.github.Project-Cepi:Kepi:de037535d0")

    // Add Highlight
    compileOnly("com.github.Project-Cepi:Highlight:dbbd6bd01f")

    // Add ItemExtension
    compileOnly("com.github.Project-Cepi:ItemExtension:3046fa2a03")

    // import kotlinx serialization
    compileOnly("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2")
}
tasks.withType<Test> {
    useJUnitPlatform()
}

configurations {
    testImplementation {
        extendsFrom(configurations.compileOnly.get())
    }
}

// Take gradle.properties and apply it to resources.
tasks {
    processResources {
        // Apply properties to extension.json
        filesMatching("META-INF/extension.json") {
            expand(project.properties)
        }
    }

    // Set name, and merge service files
    named<ShadowJar>("shadowJar") {
        archiveBaseName.set(project.name)
        mergeServiceFiles()
    }

    test { useJUnitPlatform() }

    // Make build depend on shadowJar as shading dependencies will most likely be required.
    build { dependsOn(shadowJar) }

}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}


val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions.jvmTarget = JavaVersion.VERSION_17.toString()

compileKotlin.kotlinOptions {
    freeCompilerArgs = listOf("-Xinline-classes")
}
