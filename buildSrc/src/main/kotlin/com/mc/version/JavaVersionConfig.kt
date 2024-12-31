package com.mc.version

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

fun Project.configureJavaAndKotlinOptions(javaVersion: String) {
    val androidExtension = when {
        plugins.hasPlugin("com.android.application") ->
            extensions.getByType(ApplicationExtension::class.java)

        plugins.hasPlugin("com.android.library") ->
            extensions.getByType(LibraryExtension::class.java)

        else -> null
    }

    androidExtension?.let { android ->
        android.compileOptions {
            sourceCompatibility = JavaVersion.valueOf("VERSION_$javaVersion")
            targetCompatibility = JavaVersion.valueOf("VERSION_$javaVersion")
        }
        tasks.withType(KotlinCompile::class.java).configureEach {
            kotlinOptions {
                jvmTarget = javaVersion
            }
        }
    }
}