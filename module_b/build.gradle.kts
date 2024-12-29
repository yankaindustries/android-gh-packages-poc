plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("maven-publish")
}

android {
    namespace = "com.yanka.mc.module.b"
    compileSdk = 34

    defaultConfig {
        minSdk = 30
        targetSdk = 34

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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }

    publishing {
        singleVariant("release") {
            withSourcesJar()
        }
    }
}

dependencies {
    implementation("com.yanka.mc.module:aar-poc-module-a:0.0.2")

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.0")
    implementation("com.google.android.material:material:1.8.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                groupId = "com.yanka.mc.module"
                artifactId = "aar-poc-module-b"
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