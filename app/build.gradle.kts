plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.raportmandiri"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        applicationId = "com.example.raportmandiri"
        minSdk = 24
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            optimization {
                enable = false
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    // --- TAMBAH: UI Login/Register ---
    implementation(libs.androidx.navigation.compose)               // Navigation Compose
    implementation(libs.androidx.lifecycle.viewmodel.compose)      // viewModel() di Compose
    implementation(libs.androidx.lifecycle.runtime.compose)        // collectAsStateWithLifecycle
    implementation(libs.androidx.material.icons.extended)          // ikon Visibility / VisibilityOff

    // --- TAMBAH: dipakai mulai Minggu 3 (boleh ditambahkan sekarang) ---
    implementation(libs.androidx.datastore.preferences)            // simpan token
    implementation(libs.retrofit)                                  // REST API client
    implementation(libs.retrofit.converter.gson)                   // parsing JSON
    implementation(libs.okhttp.logging.interceptor)                // log request (debug)


    testImplementation(libs.junit)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    debugImplementation(libs.androidx.compose.ui.tooling)
}