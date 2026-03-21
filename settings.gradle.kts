rootProject.name = "gort"

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

include(":gort")
include(":icons")
include(":catalog")

// Substitute the published Maven coordinate with the local project
// so multi-module builds work without publishToMavenLocal
gradle.beforeProject {
    configurations.all {
        resolutionStrategy.dependencySubstitution {
            substitute(module("zone.clanker:gort-icons"))
                .using(project(":icons"))
                .because("Use local :icons module during development")
        }
    }
}
