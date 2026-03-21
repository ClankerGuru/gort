plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.androidLibrary)
    `maven-publish`
    signing
}

kotlin {
    androidTarget {
        publishLibraryVariants("release")
    }
    jvm("desktop")
    @OptIn(org.jetbrains.kotlin.gradle.ExperimentalWasmDsl::class)
    wasmJs { browser() }
    js { browser(); nodejs() }

    // iOS + macOS native targets removed:
    // - macOS: Lucide doesn't publish macOS native variants
    // - iOS: needs macOS + Xcode to build frameworks
    // Desktop = jvm("desktop") covers Windows/Linux/macOS via JVM.

    sourceSets {
        commonMain.dependencies {
            implementation(compose.foundation)
            implementation(compose.runtime)
            implementation(compose.ui)
            api(libs.gortIcons)
            // NO Material 3 — this is intentional
        }
        commonTest.dependencies {
            implementation(kotlin("test"))
        }
    }
}

android {
    namespace = "zone.clanker.gort"
    compileSdk = 35
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

// ── Publishing ──

val javadocJar by tasks.registering(Jar::class) {
    archiveClassifier.set("javadoc")
}

publishing {
    publications.withType<MavenPublication> {
        artifact(javadocJar)

        pom {
            name.set("Gort")
            description.set("A neobrutalist design system for Compose Multiplatform. Bold. Loud. Unapologetic.")
            url.set("https://github.com/ClankerGuru/gort")
            licenses {
                license {
                    name.set("MIT License")
                    url.set("https://opensource.org/licenses/MIT")
                }
            }
            developers {
                developer {
                    id.set("ClankerGuru")
                    name.set("ClankerGuru")
                    url.set("https://github.com/ClankerGuru")
                }
            }
            scm {
                connection.set("scm:git:https://github.com/ClankerGuru/gort.git")
                developerConnection.set("scm:git:git@github.com:ClankerGuru/gort.git")
                url.set("https://github.com/ClankerGuru/gort")
            }
        }
    }

    repositories {
        maven {
            name = "MavenCentral"
            url = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
            credentials {
                username = providers.environmentVariable("ORG_GRADLE_PROJECT_sonatypeUsername")
                    .orElse(providers.gradleProperty("sonatypeUsername")).orNull
                password = providers.environmentVariable("ORG_GRADLE_PROJECT_sonatypePassword")
                    .orElse(providers.gradleProperty("sonatypePassword")).orNull
            }
        }
    }
}

signing {
    val gpgPassphrase = providers.environmentVariable("GPG_PASSPHRASE").orNull
    if (gpgPassphrase != null) {
        useGpgCmd()
    }
    sign(publishing.publications)
}

// Don't require signing for local builds
tasks.withType<Sign>().configureEach {
    onlyIf { providers.environmentVariable("GPG_PASSPHRASE").isPresent }
}
