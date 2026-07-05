import com.android.build.api.variant.ApplicationAndroidComponentsExtension
import com.android.build.api.artifact.SingleArtifact
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.kotlin.dsl.create

class ReleaseApkPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.pluginManager.apply("maven-publish")

        val myTaskProvider = target.tasks.register("publishReleaseApk", ReleaseApkTask::class.java) {
            group = "build-logic"
            description = "Собирает APK, публикует и завершает работу"
        }

        target.pluginManager.withPlugin("com.android.application") {
            val androidComponents = target.extensions.getByType(ApplicationAndroidComponentsExtension::class.java)

            androidComponents.onVariants(androidComponents.selector().withBuildType("release")) { variant ->

                val apkDirProvider = variant.artifacts.get(SingleArtifact.APK)

                val copyTaskProvider = target.tasks.register("prepareReleaseApkForPublish", CopyApkForPublishTask::class.java) {
                    apkDir.set(apkDirProvider)
                    outputFile.set(target.layout.buildDirectory.file("tmp/publish/release.apk"))
                }

                val safeApkProvider = copyTaskProvider.flatMap { it.outputFile }

                target.extensions.configure(PublishingExtension::class.java) {
                    publications {
                        create<MavenPublication>("releaseApk") {
                            groupId = "ru.khubulty"
                            artifactId = "cloudGallery"
                            version = "1.0.0"
                            artifact(safeApkProvider) {
                                extension = "apk"
                            }
                        }
                    }
                    repositories {
                        maven {
                            name = "releaseRepo"
                            url = target.uri(target.layout.buildDirectory.dir("mavenLocalRepo"))
                        }
                    }
                }

                myTaskProvider.configure {
                    apkFile.set(safeApkProvider)
                    dependsOn("publishReleaseApkPublicationToReleaseRepoRepository")
                }
            }
        }
    }
}