plugins {
    application
    kotlin("jvm") version "1.9.22"
}

application {
    mainClass.set("HelloKt")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.xerial:sqlite-jdbc:3.45.0.0")
}

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "HelloKt"
    }
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    from(configurations.runtimeClasspath.get()
        .map { if (it.isDirectory) it else zipTree(it) })
}