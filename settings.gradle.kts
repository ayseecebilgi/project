pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    // Do not use getRepositoriesMode() to avoid the incubating warning
    repositories {
        google()  // Add the Google repository
        mavenCentral()  // Add Maven Central repository
    }
}

rootProject.name = "VetPet"
include(":app")
