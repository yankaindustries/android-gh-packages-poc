import com.mc.version.Versions
import com.mc.version.configureJavaAndKotlinOptions
import org.gradle.api.Plugin
import org.gradle.api.Project

class JavaConfigPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.configureJavaAndKotlinOptions(Versions.JAVA_VERSION)
    }
}