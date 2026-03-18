plugins {
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.composeMultiplatform) apply false
    alias(libs.plugins.composeCompiler) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.androidApplication) apply false
}

val gitVersion = providers.exec {
    commandLine("git", "describe", "--tags", "--abbrev=0")
    isIgnoreExitValue = true
}.standardOutput.asText.map { it.trim().removePrefix("v") }
    .map { if (it.isBlank()) "0.0.0-LOCAL" else it }
    .getOrElse("0.0.0-LOCAL")

allprojects {
    group = "zone.clanker"
    version = gitVersion
}
