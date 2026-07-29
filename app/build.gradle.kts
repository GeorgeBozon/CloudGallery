
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
   alias(libs.plugins.hilt)
}

android {

    buildFeatures.buildConfig = true

    namespace = "ru.khubulty.cloudgallery"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        applicationId = "ru.khubulty.cloudgallery"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    defaultConfig{
        val properties = Properties()
        properties.load(project.rootProject.file("local.properties").inputStream())
        buildConfigField("String", "SUPABASE_PUBLISHABLE_KEY", "\"${properties.getProperty("SUPABASE_PUBLISHABLE_KEY")}\"")
        buildConfigField("String", "SUPABASE_URL", "\"${properties.getProperty("SUPABASE_URL")}\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debug")
        }
    }
    buildFeatures {
        compose = true
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
    implementation(projects.network.networkImpl)
    implementation(projects.core.navigation.navigationApi)
    implementation(projects.core.navigation.navigationImpl)
    implementation(projects.core.ui)
    implementation(projects.features.login.loginImpl)
    implementation(projects.features.login.loginApi)

    //navigation
    implementation(libs.bundles.navigation3)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    //compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.compose.core)
    implementation(libs.bundles.compose.debug)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    implementation(libs.compressor)
    //hilt
    implementation(libs.hilt)
    ksp(libs.hilt.compiler)

    implementation(libs.serialization.json)

}