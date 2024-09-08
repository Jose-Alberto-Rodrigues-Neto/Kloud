import com.google.cloud.tools.gradle.appengine.appyaml.AppEngineAppYamlExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.ktor)
    alias(libs.plugins.googleCloudToolsAppengine)
    alias(libs.plugins.shadow)

    kotlin("plugin.serialization") version libs.versions.kotlin
    application
}

group = "project.kloud"
version = "1.0.0"
application {
    mainClass.set("io.ktor.server.netty.EngineMain")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=${extra["development"] ?: "false"}")
}

configure<AppEngineAppYamlExtension> {
    stage {
        setArtifact("build/libs/${project.name}-all.jar")
        setAppEngineDirectory("src/main/appengine")
    }
    deploy {
        version = "GCLOUD_CONFIG"
        projectId = "kloud-test"
    }
}


tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xcontext-receivers"
    }
}

dependencies {
    implementation(projects.shared)
    implementation(libs.logback)
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.netty)
    testImplementation(libs.ktor.server.tests)
    testImplementation(libs.kotlin.test.junit)
    testImplementation(libs.ktor.server.tests)
    implementation(libs.ktor.server.statuspages)
    implementation(libs.ktor.server.auth)
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.cio)
    implementation(libs.ktor.server.content.negotiation)
    implementation(libs.ktor.server.sessions)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.arrow.core)
    implementation(libs.arrow.fx.coroutines)
    implementation("com.github.dotenv-org:dotenv-vault-kotlin:0.0.2")
    implementation("com.google.api-client:google-api-client:2.4.1")
}