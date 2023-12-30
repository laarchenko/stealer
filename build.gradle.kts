plugins {
    id("java")
    id ("org.springframework.boot") version "3.0.6"
    id ("io.spring.dependency-management") version "1.1.0"
    id ("org.liquibase.gradle") version "2.2.0"
    id ("com.google.cloud.artifactregistry.gradle-plugin") version "2.2.1"
    id ("com.diffplug.spotless") version "6.20.0"
}

group = "com.example.company"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
    mavenLocal ()
}
dependencies {
    compileOnly("org.projectlombok:lombok:1.18.28")
    annotationProcessor ("org.projectlombok:lombok:1.18.28")
    annotationProcessor ("org.projectlombok:lombok")
    annotationProcessor ("org.mapstruct:mapstruct-processor:1.5.5.Final")
    annotationProcessor ("org.projectlombok:lombok-mapstruct-binding:0.2.0")
    implementation ("org.springframework.boot:spring-boot-starter")
    implementation ("org.springframework.boot:spring-boot-starter-web")
    implementation ("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation ("org.springframework.boot:spring-boot-starter-validation")
    implementation ("org.springframework.boot:spring-boot-starter-actuator")
    implementation ("org.springframework.boot:spring-boot-starter-aop")
    implementation ("org.springframework.boot:spring-boot-starter-cache")
    //implementation ("org.springframework.boot:spring-boot-starter-security")
    implementation ("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.1.0")
    implementation ("org.mapstruct:mapstruct:1.5.5.Final")
    implementation ("org.liquibase:liquibase-core")
    implementation ("io.github.openfeign:feign-core:12.3")
    implementation ("org.seleniumhq.selenium:selenium-java:3.141.59")

    runtimeOnly ("org.postgresql:postgresql")
    testCompileOnly ("org.projectlombok:lombok:1.18.28")

    testAnnotationProcessor ("org.projectlombok:lombok:1.18.28")

    testImplementation ("org.springframework.boot:spring-boot-starter-test")
    testImplementation ("io.github.glytching:junit-extensions:2.6.0")
    testImplementation ("io.rest-assured:json-schema-validator:5.3.0")
    testImplementation("io.rest-assured:spring-mock-mvc:3.0.0")
    testImplementation (files("src/test/resources"))
}

tasks.withType <JavaCompile> {
    val compilerArgs = options.compilerArgs
    compilerArgs.add("-Amapstruct.defaultComponentModel=spring")
}
tasks.named <Test>("test") {
    useJUnitPlatform()
    systemProperty("junit.jupiter.extensions.autodetection.enabled", true)
}
spotless {
    java {
        target("src/*/java/**/*.java")
        palantirJavaFormat ()
        importOrder ()
        removeUnusedImports ()
        formatAnnotations ()
    }
}