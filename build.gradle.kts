import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.5.6"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.5.31"
    kotlin("plugin.spring") version "1.5.31"
    // Used for mocking framework
    // https://kotlinlang.org/docs/all-open-plugin.html
    id("org.jetbrains.kotlin.plugin.allopen") version "1.5.31"
    // Use for hibernate framework
    // https://kotlinlang.org/docs/no-arg-plugin.html
    id("org.jetbrains.kotlin.plugin.noarg") version "1.5.31"
    // https://kotlinlang.org/docs/no-arg-plugin.html#jpa-support
    id("org.jetbrains.kotlin.plugin.jpa") version "1.5.31"
}

group = "com.german"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    jcenter()
    mavenCentral()
}

// We have to add this because of the issue:
// "Getter methods of lazy classes cannot be final. Example: com.german.notificationappkotlin.domain.Client#getId"
// This is for every attribute, actually not only the ID of the Client
allOpen {
    annotation("javax.persistence.Entity")
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    implementation("org.springframework.kafka:spring-kafka")
    testImplementation("org.springframework.kafka:spring-kafka-test")
    testImplementation("io.mockk:mockk:1.12.0")

    developmentOnly("org.springframework.boot:spring-boot-devtools")
    runtimeOnly("org.postgresql:postgresql")

    implementation("com.google.code.gson:gson:2.8.6")
    implementation("org.springframework.boot:spring-boot-starter-hateoas")
    implementation("org.jetbrains.exposed:exposed-core:0.24.1")
    implementation("org.jetbrains.exposed:exposed-dao:0.24.1")
    implementation("org.jetbrains.exposed:exposed-jdbc:0.24.1")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
}

springBoot {
    mainClassName = "com.german.notificationappkotlin.NotificationAppKotlinApplication"
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}