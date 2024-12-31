plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
}

//noinspection UseTomlInstead since toml can't be used in buildSrc scripts
dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.24")

    implementation("com.android.tools.build:gradle:8.7.3")
}

// Ref: https://docs.gradle.org/current/userguide/plugins.html#sec:buildsrc_plugins_dsl
gradlePlugin {
    plugins {
        create("mcPlugins") {
            id = "mc-java-config"
            implementationClass = "JavaConfigPlugin"
        }
    }
}