plugins {
    id("java")
    id("maven-publish")
}

group = "bet.astral"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains:annotations:26.0.2")
    compileOnly("org.apiguardian:apiguardian-api:1.1.2")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
    withSourcesJar()
    withJavadocJar()
}

tasks.test {
    useJUnitPlatform()
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = "bet.astral.jtext.Test" // Replace with your main class
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "bet.astral"
            artifactId = "jtext"

            from(components["java"])
        }
    }
}

