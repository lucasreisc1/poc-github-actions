plugins {
    kotlin("jvm") version "1.6.10"
    kotlin("plugin.allopen") version "1.6.10"
    id("io.quarkus")
}

repositories {
    mavenCentral()
    mavenLocal()
    maven {
        url = uri("https://s3-us-west-2.amazonaws.com/dynamodb-local/release")
    }
}

val quarkusPlatformGroupId: String by project
val quarkusPlatformArtifactId: String by project
val quarkusPlatformVersion: String by project

dependencies {
    implementation(enforcedPlatform("${quarkusPlatformGroupId}:${quarkusPlatformArtifactId}:${quarkusPlatformVersion}"))
    implementation(enforcedPlatform("${quarkusPlatformGroupId}:quarkus-amazon-services-bom:${quarkusPlatformVersion}"))
    implementation(enforcedPlatform("${quarkusPlatformGroupId}:quarkus-camel-bom:${quarkusPlatformVersion}"))

    implementation("io.quarkus:quarkus-amazon-lambda")
    implementation("io.quarkus:quarkus-hibernate-orm")
    implementation("io.quarkus:quarkus-jdbc-h2")
    implementation("io.quarkus:quarkus-rest-client")
    implementation("io.quarkus:quarkus-kotlin")
    implementation("io.quarkiverse.amazonservices:quarkus-amazon-dynamodb")
    implementation("io.quarkus:quarkus-amazon-lambda-rest")
    implementation("io.quarkus:quarkus-resteasy")
    implementation("io.quarkiverse.amazonservices:quarkus-amazon-s3")
    implementation("io.quarkiverse.amazonservices:quarkus-amazon-sqs")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("io.quarkus:quarkus-arc")
    implementation("com.amazonaws:aws-java-sdk-dynamodb:1.12.153")
    implementation("io.quarkus:quarkus-rest-client-jackson")
    implementation("com.google.code.gson:gson:2.7")
    implementation("io.projectreactor:reactor-core:3.4.14")

    // Dependencia está sendo utilizada por conta do erro ClassDefNotFoundError do DynamoDbBean causada com a integração com quarkus.
    implementation("me.nithanim.quarkus:quarkus-amazon-dynamodb-enhanced:1.0.1")


    testImplementation("io.quarkus:quarkus-junit5")
    testImplementation("io.rest-assured:rest-assured")
    testImplementation ("org.assertj:assertj-core:3.22.0")
    testImplementation("com.amazonaws:DynamoDBLocal:1.15.0")
    testImplementation("org.testcontainers:testcontainers:1.14.1")
    testImplementation("org.testcontainers:junit-jupiter:1.14.1")
}

group = "com.involves.poc.ri"
version = "1.0.0-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

allOpen {
    annotation("javax.ws.rs.Path")
    annotation("javax.enterprise.context.ApplicationScoped")
    annotation("io.quarkus.test.junit.QuarkusTest")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = JavaVersion.VERSION_11.toString()
    kotlinOptions.javaParameters = true
}
