plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.henryhiles.qweather"
    compileSdk = 33

    signingConfigs {
        create("release") {
            storeFile = file("${rootDir}/keystore.jks")
            keyPassword = System.getenv("KEY_PASSWORD")
            keyAlias = "key0"
            storePassword = System.getenv("STORE_PASSWORD")
        }
    }

    defaultConfig {
        applicationId = "com.henryhiles.qweather"
        minSdk = 30
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        named("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            signingConfig = signingConfigs.getByName("release")
            setProguardFiles(
                listOf(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            )
        }
        named("debug") {
            applicationIdSuffix = ".debug"
        }
    }
    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.10.0")
    implementation("androidx.compose.material3:material3:1.1.0-rc01")
    implementation("androidx.activity:activity-compose:1.7.1")
    implementation("androidx.core:core-ktx:1.10.0")

    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.3")
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.0.3")

    // Lifecycle
    val lifecycleVersion = "2.6.1"

    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycleVersion")

    // Compose
    val composeVersion = "1.4.0"

    implementation("androidx.compose.ui:ui-tooling-preview:$composeVersion")
    implementation("androidx.compose.ui:ui:$composeVersion")
    implementation("androidx.compose.material:material-icons-extended:$composeVersion")
    debugImplementation("androidx.compose.ui:ui-tooling:$composeVersion")

    // Voyager
    val voyagerVersion = "1.0.0-rc04"

    implementation("cafe.adriel.voyager:voyager-navigator:$voyagerVersion")
    implementation("cafe.adriel.voyager:voyager-tab-navigator:$voyagerVersion")
    implementation("cafe.adriel.voyager:voyager-transitions:$voyagerVersion")
    implementation("cafe.adriel.voyager:voyager-androidx:$voyagerVersion")
    implementation("cafe.adriel.voyager:voyager-koin:$voyagerVersion")

    // Koin
    val koinVersion = "3.2.0"

    implementation("io.insert-koin:koin-core:$koinVersion")
    implementation("io.insert-koin:koin-android:$koinVersion")
    implementation("io.insert-koin:koin-androidx-compose:$koinVersion")

    // Retrofit
    val retrofitVersion = "2.9.0"
    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation("com.squareup.retrofit2:converter-moshi:$retrofitVersion")

    // Accompanist
    val accompanistVersion = "0.30.0"
    implementation("com.google.accompanist:accompanist-permissions:$accompanistVersion")
}
