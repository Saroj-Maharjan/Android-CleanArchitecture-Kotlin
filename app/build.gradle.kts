@file:Suppress("UnstableApiUsage")

class AppConfig {
    val id = "com.fernandocejas.sample"
    val versionCode = 1
    val versionName = "1.0"

    val compileSdk = libs.versions.compileSdk.get().toInt()
    val minSdk = libs.versions.minSdk.get().toInt()
    val targetSdk = libs.versions.targetSdk.get().toInt()

    val javaVersion = JavaVersion.VERSION_17
    val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
}

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.kotlin.parcelize)
}

android {
    val appConfig = AppConfig()

    namespace = appConfig.id
    compileSdk = appConfig.compileSdk

    defaultConfig {
        applicationId = appConfig.id

        minSdk = appConfig.minSdk
        targetSdk = appConfig.targetSdk
        versionCode = appConfig.versionCode
        versionName = appConfig.versionName

        testInstrumentationRunner = appConfig.testInstrumentationRunner
    }

    compileOptions {
        sourceCompatibility = appConfig.javaVersion
        targetCompatibility = appConfig.javaVersion
    }

    kotlinOptions {
        jvmTarget = appConfig.javaVersion.toString()
    }

    buildFeatures {
        viewBinding = true
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
        }
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

dependencies {
    //Compile time dependencies
    kapt(libs.androidx.lifecycle.compiler)

    // Application dependencies
    implementation(libs.kotlin.stdlib.jdk8)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.koin.android)
    implementation(libs.android.appcompat)
    implementation(libs.android.core.ktx)
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.extensions)
    implementation(libs.androidx.cardview)
    implementation(libs.androidx.recyclerview)
    implementation(libs.material)
    implementation(libs.androidx.annotation)
    implementation(libs.glide)
    implementation(libs.converter.gson)

    // Unit/Integration tests dependencies
    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.kluent.android)
    testImplementation(libs.robolectric)

    // UI tests dependencies
    androidTestImplementation(libs.androidx.runner)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.rules)
    androidTestImplementation(libs.androidx.espresso.intents)

    // Development/Tooling dependencies
    debugImplementation(libs.leakcanary.android)
}
