import org.gradle.api.provider.Property

interface ReleaseApkExtension {
    val repoDirectoryName: Property<String>
}