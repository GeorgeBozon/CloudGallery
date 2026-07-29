plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.serialization)
}

kotlin {
    jvmToolchain(17)
}

dependencies{
    implementation(libs.serialization.core)
    api(libs.navigation.compose.runtime)
}
