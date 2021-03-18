package io.github.dreamylost

import io.github.dreamylost.task.LeetcodeGradleTask
import org.gradle.api.Plugin
import org.gradle.api.Project

open class LeetcodeGradlePlugin : Plugin<Project> {
    override fun apply(project: Project) {
        val tasks = project.tasks
        val extensions = project.extensions
        tasks.create("leetcodeCodegen", LeetcodeGradleTask::class.java)
        extensions.create("leetcodeExtension", LeetcodePluginExtension::class.java)
    }
}

open class LeetcodePluginExtension {
    var templateSourceCode: String? = null
    var templateName: String? = null
    var customData: Map<String, Any> = mapOf()
}