import org.gradle.api.DefaultTask
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction

abstract class CopyApkForPublishTask : DefaultTask() {
    @get:InputDirectory
    abstract val apkDir: DirectoryProperty

    @get:OutputFile
    abstract val outputFile: RegularFileProperty

    @TaskAction
    fun doWork() {
        val dir = apkDir.get().asFile
        val apk = dir.walk().firstOrNull { it.extension == "apk" }
            ?: error("APK файл не найден в директории: ${dir.absolutePath}")

        apk.copyTo(outputFile.get().asFile, overwrite = true)
    }
}