//import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
//
//plugins {
//    id("org.springframework.boot") version "2.5.5"
//    id("io.spring.dependency-management") version "1.0.11.RELEASE"
//    war
//    kotlin("jvm") version "1.5.31"
//    kotlin("plugin.spring") version "1.5.31"
//    kotlin("plugin.jpa") version "1.5.31"
//}
//
//group = "com.german"
//version = "0.0.1-SNAPSHOT"
//java.sourceCompatibility = JavaVersion.VERSION_1_8
//
//repositories {
//    mavenCentral()
//}
//
//dependencies {
//    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
//    implementation("org.springframework.boot:spring-boot-starter-mustache")
//    implementation("org.springframework.boot:spring-boot-starter-web")
//    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
//    implementation("org.jetbrains.kotlin:kotlin-reflect")
//    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
//    developmentOnly("org.springframework.boot:spring-boot-devtools")
////    implementation("com.h2database:h2")
////    runtimeOnly("com.h2database:h2")
////    testImplementation("com.h2database:h2:1.3.148")
//    // https://mvnrepository.com/artifact/org.postgresql/postgresql
////    runtimeOnly("com.h2database:h2")
//    implementation("org.postgresql:postgresql:42.1.4")
//    providedRuntime("org.springframework.boot:spring-boot-starter-tomcat")
//    testImplementation("org.springframework.boot:spring-boot-starter-test")
//}
//
//tasks.withType<KotlinCompile> {
//    kotlinOptions {
//        freeCompilerArgs = listOf("-Xjsr305=strict")
//        jvmTarget = "1.8"
//    }
//}
//
//tasks.withType<Test> {
//    useJUnitPlatform()
//}
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.5.6"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.5.31"
    kotlin("plugin.spring") version "1.5.31"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.5.31"
    id("org.jetbrains.kotlin.plugin.noarg") version "1.5.31"
    id("org.jetbrains.kotlin.plugin.jpa") version "1.5.31"
}

group = "com.german"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    jcenter()
    mavenCentral()
}



dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    runtimeOnly("org.postgresql:postgresql")
    testImplementation("org.springframework.boot:spring-boot-starter-test")

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

//noArg {
//    annotation("com.lindroid.projectname.annotation.NoArg")
//}
