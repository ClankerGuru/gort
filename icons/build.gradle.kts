import org.gradle.internal.os.OperatingSystem

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

    if (OperatingSystem.current().isMacOsX) {
        // iOS targets — same as :gort
        listOf(iosArm64(), iosSimulatorArm64()).forEach {
            it.binaries.framework {
                baseName = "gort-icons"
                isStatic = true
            }
        }

        // macOS native — the whole reason this module exists
        listOf(macosArm64(), macosX64()).forEach {
            it.binaries.framework {
                baseName = "gort-icons"
                isStatic = true
            }
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(compose.foundation)
            implementation(compose.runtime)
            implementation(compose.ui)
        }
    }
}

android {
    namespace = "zone.clanker.gort.icons"
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
        groupId = "zone.clanker"
        artifactId = "gort-icons"
        artifact(javadocJar)

        pom {
            name.set("Gort Icons — Lucide")
            description.set("Lucide icons for Compose Multiplatform, bundled for all KMP targets including macOS native. Part of the Gort design system.")
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
                developer {
                    id.set("composablehorizons")
                    name.set("Composable Horizons (original Lucide CMP authors)")
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
