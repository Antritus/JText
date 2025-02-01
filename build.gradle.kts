plugins {
    id("java")
    id("maven-publish")
}

group = "bet.astral"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains:annotations:26.0.2")

    // Jansi
    // https://mvnrepository.com/artifact/com.diogonunes/JColor
    implementation("com.diogonunes:JColor:5.5.1")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

java {
    withSourcesJar()
    withJavadocJar()
}

tasks.test {
    useJUnitPlatform()
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

