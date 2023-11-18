import org.jetbrains.kotlin.gradle.plugin.mpp.pm20.util.archivesName

@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    `maven-publish`
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.parcelize)
    alias(libs.plugins.serialization)
}

group = "com.github.dinaraparanid"
version = "0.1.0.1"

android {
    namespace = "com.paranid5.yturlextractorkt"
    compileSdk = 34

    defaultConfig {
        minSdk = 21
        version = "1.0"
        archivesName = "yturlextractorkt"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
        freeCompilerArgs = listOf("-Xcontext-receivers")
    }
    publishing {
        singleVariant("release") {
            withSourcesJar()
            withJavadocJar()
        }
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)

    implementation(libs.ktor.client.core)
    implementation(libs.ktor.serialization.kotlinx.json)

    implementation(libs.js.evaluator) {
        exclude(module = "appcompat-v7")
    }
}

afterEvaluate {
    publishing {
        publications {
            register<MavenPublication>("release") {
                from(components["release"])
                groupId = "com.github.dinaraparanid"
                artifactId = "yturlextractorkt"
                version = "0.1.0.1"
            }
        }
    }
}