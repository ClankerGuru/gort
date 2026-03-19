plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.androidApplication)
}

kotlin {
    jvm("desktop")
    androidTarget()
    @OptIn(org.jetbrains.kotlin.gradle.ExperimentalWasmDsl::class)
    wasmJs { browser() }

    sourceSets {
        commonMain.dependencies {
            implementation(project(":gort"))
            implementation(compose.foundation)
            implementation(compose.runtime)
            implementation(compose.ui)
        }

        val desktopMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
            }
        }

        androidMain.dependencies {
            implementation("androidx.activity:activity-compose:1.10.1")
        }
    }
}

android {
    namespace = "zone.clanker.gort.catalog"
    compileSdk = 35

    defaultConfig {
        applicationId = "zone.clanker.gort.catalog"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "0.1.0"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

compose.desktop {
    application {
        mainClass = "zone.clanker.gort.catalog.MainKt"
    }
}
