package io.github.dreamylost

import io.github.dreamylost.task.LeetcodeGradleTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.TaskContainer

open class LeetcodeGradlePlugin : Plugin<Project> {
    override fun apply(project: Project) {
        val tasks: TaskContainer = project.tasks
        tasks.create("leetcodeCodegen", LeetcodeGradleTask::class.java)
    }
}