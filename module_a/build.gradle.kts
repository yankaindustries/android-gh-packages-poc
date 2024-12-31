plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("maven-publish")
    id("mc-java-config")
}

android {
    namespace = "com.yanka.mc.module.a"
    compileSdk = 34

    defaultConfig {
        minSdk = 30
        aarMetadata {
            minCompileSdk = 28
        }

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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

    publishing {
        singleVariant("release") {
            withSourcesJar()
        }
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                groupId = "com.yanka.mc.module"
                artifactId = "aar-poc-module-a"
                version = "0.0.3"
                from(components["release"])
            }
        }

        // Android Developer docs that explain how to create an android module for publishing
        // https://developer.android.com/studio/publish-library/upload-library#local-repo
        // Github docs that explain how to publish to github packages
        // https://docs.github.com/en/packages/working-with-a-github-packages-registry/working-with-the-gradle-registry#example-using-gradle-groovy-for-a-single-package-in-a-repository
        repositories {
            maven {
                name = "GitHubPackages"
                url = uri("https://maven.pkg.github.com/yankaindustries/android-gh-packages-poc")

                val githubUsername = System.getenv("MC_ORG_ANDROID_GH_PACKAGES_USERNAME")
                val githubToken = System.getenv("MC_ORG_ANDROID_GH_PACKAGES_TOKEN")

                if (githubUsername.isNullOrEmpty() || githubToken.isNullOrEmpty()) {
                    logger.lifecycle(
                        "Missing credentials for GitHub Packages. " +
                                "Ensure environment variables are set for publishing. " +
                                "This may be safely ignored for local builds."
                    )
                    return@maven
                }

                logger.lifecycle("GH Packages username: ${if (!githubUsername.isNullOrEmpty()) "PRESENT" else "NOT SET"}")
                logger.lifecycle("GH Packages token: ${if (!githubToken.isNullOrEmpty()) "PRESENT" else "NOT SET"}")
                credentials {
                    username = githubUsername
                    password = githubToken
                }
            }
        }
    }
}