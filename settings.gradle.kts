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
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "NeuroTeacher"

include(":app")

// Core modules
include(":core:data")
include(":core:network")
include(":core:domain")
include(":core:utils")
include(":core:db")
include(":core:ui")

// Feature modules
include(":feature:test-creation")
include(":feature:test-taking")
include(":feature:home")


include(":core:build-config:api")
include(":core:build-config:impl")
include(":feature:auth")
