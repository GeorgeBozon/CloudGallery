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

        //финальная таска
        val myTaskProvider =
            target.tasks.register("publishReleaseApk", ReleaseApkTask::class.java) {
                group = "build-logic"
                description = "Собирает APK, публикует и завершает работу"
            }

        val taskExtension =
            target.extensions.create("publishReleaseApk", ReleaseApkExtension::class.java)

        taskExtension.repoDirectoryName.convention("mavenLocalRepo")

        target.pluginManager.withPlugin("com.android.application") {
            val androidComponents =
                target.extensions.getByType(ApplicationAndroidComponentsExtension::class.java)

            androidComponents.onVariants(
                androidComponents.selector().withBuildType("release")
            ) { variant ->

                // 1. Получаем Провайдер ПАПКИ от Android
                val apkDirProvider = variant.artifacts.get(SingleArtifact.APK)

                // 2. Регистрируем нашу задачу-мост
                val copyTaskProvider = target.tasks.register(
                    "prepareReleaseApkForPublish",
                    CopyApkForPublishTask::class.java
                ) {
                    apkDir.set(apkDirProvider)
                    // Указываем четкий промежуточный файл для мавена
                    outputFile.set(target.layout.buildDirectory.file("tmp/publish/release.apk"))
                }

                // 3. Вытаскиваем "безопасный" провайдер файла из нашей задачи
                val safeApkProvider = copyTaskProvider.flatMap { it.outputFile }

                // 4. Настраиваем публикацию
                target.extensions.configure(PublishingExtension::class.java) {
                    publications {
                        create<MavenPublication>("releaseApk") {
                            groupId = "ru.khubulty"
                            artifactId = "cloudGallery"
                            version = "1.0.0"

                            // Передаем безопасный файл. Теперь Gradle сам построит граф:
                            // assembleRelease -> prepareReleaseApkForPublish -> generatePom... -> publish...
                            artifact(safeApkProvider) {
                                extension = "apk"
                            }
                        }
                    }
                    repositories {
                        maven {
                            name = "releaseRepo"
                            url = target.uri(
                                target.layout.buildDirectory.dir(
                                    taskExtension.repoDirectoryName.get()
                                )
                            )
                        }
                    }
                }

                // 5. Финальная связка
                myTaskProvider.configure {
                    //эта таска тоже получит безопасный файл
                    apkFile.set(safeApkProvider)
                    dependsOn("publishReleaseApkPublicationToReleaseRepoRepository")
                }
            }
        }
    }
}