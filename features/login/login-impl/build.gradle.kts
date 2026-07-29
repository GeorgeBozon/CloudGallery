plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.android.built.in1.kotlin)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
}

android {
    namespace = "ru.khubulty.loginImpl"
    compileSdk = 37

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        compose = true
    }

    buildTypes {
        release {
            optimization {
                enable = false
            }
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

kotlin {
    jvmToolchain(17)
}

dependencies {
    //internal deps
    implementation(projects.features.login.loginApi)
    implementation(projects.network.networkApi)
    implementation(projects.core.ui)
    implementation(projects.core.navigation.navigationApi)

    implementation(libs.androidx.core.ktx)
    implementation(libs.android.material)

    //navigation
    implementation(libs.bundles.navigation3)
    //compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.compose.core)
    implementation(libs.bundles.compose.debug)

    //tests
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    //hilt
    implementation(libs.hilt)
    implementation(libs.hilt.viewmodel.compose)
    ksp(libs.hilt.compiler)
}

