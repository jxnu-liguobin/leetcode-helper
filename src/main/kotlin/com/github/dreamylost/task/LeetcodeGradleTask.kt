package com.github.dreamylost.task

import com.fasterxml.jackson.module.kotlin.readValue
import com.github.dreamylost.*
import io.github.dreamylost.*
import com.github.dreamylost.invoker.ClientInvoker
import com.github.dreamylost.invoker.Jackson
import com.github.dreamylost.invoker.LeetcodeException
import com.github.dreamylost.invoker.ServerConfig
import com.github.dreamylost.registry.FreeMarkerTemplatesRegistry
import io.github.graphql.model.CodeSnippetNodeTO
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Optional
import org.gradle.api.tasks.TaskAction
import java.io.File
import kotlin.streams.toList

abstract class LeetcodeGradleTask : DefaultTask() {

    // 必填，这里使用Option是为了兼容属性变量和task输入
    @get:Input
    @get:Optional
    abstract var questionTitle: String?

    @get:Input
    @get:Optional
    abstract var serverConfig: ServerConfig?

    @get:Input
    @get:Optional
    abstract var generatedLanguage: GeneratedLanguage?

    @get:Input
    @get:Optional
    abstract var packageName: String?

    @get:Input
    @get:Optional
    abstract var prefix: String?

    @get:Input
    @get:Optional
    abstract var srcFolder: String?

    @TaskAction
    open fun leetcodeHelper() {
        val leetcodeExtension = project.extensions.getByType(LeetcodePluginExtension::class.java)
        // 优先使用属性中获取的变量
        setProperties()
        // 均没有，则使用默认
        setDefaultValues()
        // 注册自定义模板
        if (leetcodeExtension.templateSourceCode != null && leetcodeExtension.customData.isNotEmpty()) {
            generatedLanguage?.let {
                FreeMarkerTemplatesRegistry.customRegister(
                    leetcodeExtension.templateName!!, leetcodeExtension.templateSourceCode!!, it
                )
            }
        }
        println(
            "config:  \n questionTitle:${questionTitle}" +
                    "\n serverConfig:${serverConfig}" +
                    "\n generatedLanguage:${generatedLanguage}" +
                    "\n packageName:${packageName}" +
                    "\n prefix:${prefix}" +
                    "\n srcFolder:$srcFolder" +
                    "\n customTemplate:${leetcodeExtension.templateName}"
        )
        val question = ClientInvoker.getQuestion(serverConfig!!, questionTitle!!)
        val langCodes: List<Pair<String?, CodeSnippetNodeTO?>>? =
            question.codeSnippets?.stream()?.map { Pair(it?.langSlug, it) }?.toList()
        val codeNode = langCodes?.find { it.first == generatedLanguage!!.language }
        val data = mapOf(
            Pair(Constants.CLASS_NAME, question.questionFrontendId),
            Pair(Constants.PREFIX, prefix),
            Pair(Constants.PACKAGE, packageName),
            Pair(Constants.CODE, codeNode?.second?.code),
        )
        LeetcodeFileCreator.createFile(
            data.plus(leetcodeExtension.customData),
            buildSrcFolder(srcFolder, generatedLanguage!!),
            generatedLanguage!!
        )
    }

    private fun buildSrcFolder(srcFolder: String?, language: GeneratedLanguage): File {
        val langs = listOf(GeneratedLanguage.Kotlin, GeneratedLanguage.Scala, GeneratedLanguage.Java)
        return if (srcFolder != null) {
            File(srcFolder)
        } else {
            when {
                langs.contains(language) -> {
                    File(super.getProject().projectDir.toPath().toString() + "/src/main/" + language.language)
                }
                language == GeneratedLanguage.Rust -> {
                    File("rust-leetcode/src")
                }
                else -> {
                    throw LeetcodeException("language not supported: ", language.toString(), null)
                }
            }
        }
    }

    private fun setProperties() {
        if (project.properties.containsKey("questionTitle")) {
            questionTitle = project.properties["questionTitle"].toString()
        }
        if (project.properties.containsKey("generatedLanguage")) {
            generatedLanguage =
                ExtractGeneratedLanguage.extract(project.properties["generatedLanguage"].toString().toLowerCase())
        }
        if (project.properties.containsKey("serverConfig")) {
            serverConfig = Jackson.objectMapper.readValue(project.properties["serverConfig"].toString())
        }
        if (project.properties.containsKey("packageName")) {
            packageName = project.properties["packageName"].toString()
        }
        if (project.properties.containsKey("prefix")) {
            prefix = project.properties["prefix"].toString()
        }
    }

    private fun setDefaultValues() {
        if (questionTitle == null) {
            questionTitle = project.properties["questionTitle"].toString()
        }
        if (questionTitle == null) {
            throw LeetcodeException("questionTitle cannot be null", null, null)
        }
        if (generatedLanguage == null) {
            generatedLanguage = GeneratedLanguage.Kotlin
        }
        if (serverConfig == null) {
            serverConfig = ServerConfig.defaultConfig()
        }

        if (prefix == null) {
            prefix = "Leetcode_"
        }
        if (packageName == null) {
            packageName = "io.github.dreamylost"
        }

        if (generatedLanguage == GeneratedLanguage.Rust) {
            if (prefix!!.length > 2) {
                prefix = prefix!![0].toLowerCase() + prefix!!.substring(1)
            }
        }
    }
}