pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("https://maven.pkg.github.com/yankaindustries/android-gh-packages-poc")
            authentication {
                create<BasicAuthentication>("basic")
            }

            var githubUsername: String? = null
            var githubToken: String? = null

            try {
                val props = java.util.Properties()
                val file = File("./local.properties")
                if (file.exists()) {
                    props.load(file.inputStream())
                    githubUsername = props.getProperty("gh-packages-username")
                    githubToken = props.getProperty("gh-packages-token")
                }
            } catch (e: Exception) {
                println("Error reading local.properties: ${e.message}")
            }

            if (githubUsername.isNullOrBlank() || githubToken.isNullOrBlank()) {
                println("Developer's local credentials not found. Falling back to env vars.")
                githubUsername = System.getenv("MC_ORG_ANDROID_GH_PACKAGES_USERNAME")
                githubToken = System.getenv("MC_ORG_ANDROID_GH_PACKAGES_TOKEN")

                println("GH Packages username length: ${githubUsername?.length ?: "NOT SET"}")
                println("GH Packages token length: ${githubToken?.length ?: "NOT SET"}")
            }

            credentials {
                username = githubUsername
                password = githubToken
            }
        }
    }
}

rootProject.name = "Aar PoC"

include(":app")
include(":module_a")
include(":module_b")
