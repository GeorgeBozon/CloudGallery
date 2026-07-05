import org.gradle.api.DefaultTask
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.TaskAction

abstract class ReleaseApkTask : DefaultTask() {
    @get:InputFile
    abstract val apkFile: RegularFileProperty

    @TaskAction
    fun doWork() {
        val file = apkFile.get().asFile
        println("Публикация завершена! Мой файл лежит тут: ${file.absolutePath}")
    }
}