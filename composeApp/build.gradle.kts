import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    id("org.jetbrains.kotlin.plugin.serialization") version "1.5.21"
}

dependencies {
    implementation("androidx.compose.material3:material3:1.3.0")
    implementation("androidx.compose.material3:material3-window-size-class:1.3.0")
    implementation("androidx.compose.material3:material3-adaptive-navigation-suite:1.3.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.0")
    implementation(libs.androidx.navigation.runtime.ktx)
    implementation(libs.navigation.compose)
    implementation("com.github.tehras:charts:0.2.4-alpha")
    implementation(libs.androidx.material3.android)
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "11"
            }
        }
    }

    sourceSets {
        androidMain.dependencies {
            implementation(libs.compose.ui.tooling.preview)
            implementation(libs.androidx.activity.compose)
            implementation("com.github.tehras:charts:0.2.4-alpha")
            // Retrofit
            implementation("com.squareup.retrofit2:retrofit:2.9.0")
            // Retrofit with Scalar Converter
            implementation("com.squareup.retrofit2:converter-scalars:2.9.0")
            implementation("androidx.compose.material3:material3:1.3.0")
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation("androidx.compose.material3:material3:1.3.0")
            implementation(projects.shared)
            implementation("com.github.tehras:charts:0.2.4-alpha")
            // Retrofit
            implementation("com.squareup.retrofit2:retrofit:2.9.0")
            // Retrofit with Scalar Converter
            implementation("com.squareup.retrofit2:converter-scalars:2.9.0")

            implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.0")

        }
    }
}

android {
    namespace = "project.kloud"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        applicationId = "project.kloud"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    dependencies {
        debugImplementation(libs.compose.ui.tooling)
    }
}

