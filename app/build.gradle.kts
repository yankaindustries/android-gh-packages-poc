plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("mc-java-config")
}

android {
    namespace = "com.yanka.mc.aar"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.yanka.mc.aar"
        minSdk = 30
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
}

dependencies {
    // These MC libraries need to be available from the dependency artifactories.
    // Check settings.gradle for the dependency resolution configs.
    implementation(libs.aar.poc.module.a)
    implementation(libs.aar.poc.module.b)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
