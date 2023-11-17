import org.jetbrains.kotlin.gradle.plugin.mpp.pm20.util.archivesName

@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.parcelize)
    alias(libs.plugins.serialization)
    id("maven-publish")
}

android {
    namespace = "com.paranid5.yt_url_extractor_kt"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.paranid5.yt_url_extractor_kt"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        version = "1.0"
        archivesName = "yt-url-extractor-kt"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
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

publishing {
    publications {
        register<MavenPublication>("release") {
            groupId = "com.github.dinaraparanid"
            artifactId = "YtUrlExtractorKt"
            version = "1.0.0"

            afterEvaluate {
                from(components["release"])
            }
        }
    }
}