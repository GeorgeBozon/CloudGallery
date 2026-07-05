plugins {
    `kotlin-dsl`
    `maven-publish`
}

gradlePlugin {
    plugins {
        create("myReleasePlugin") {
            id = "ru.khubulty.cloudgallery.release"
            implementationClass = "ReleaseApkPlugin"
        }
    }
}

dependencies{
    compileOnly("com.android.tools.build:gradle:9.0.1")
}