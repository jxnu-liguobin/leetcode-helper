package io.github.dreamylost.task

import io.github.dreamylost.Constants
import io.github.dreamylost.ExtractGeneratedLanguage
import io.github.dreamylost.GeneratedLanguage
import io.github.dreamylost.LeetcodeFileCreator
import io.github.dreamylost.invoker.ClientInvoker
import io.github.dreamylost.invoker.Jackson
import io.github.dreamylost.invoker.ServerConfig
import io.github.graphql.model.CodeSnippetNodeTO
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import java.io.File
import kotlin.streams.toList

open class LeetcodeGradleTask : DefaultTask() {

    lateinit var questionTitle: String
    lateinit var serverConfig: ServerConfig
    lateinit var generatedLanguage: GeneratedLanguage
    var packageName: String = "io.github.dreamylost"
    var prefix: String = "Leetcode_"

    @TaskAction
    fun leetcodeHelper() {
        if (super.getProject().hasProperty("questionTitle")) {
            questionTitle = super.getProject().properties["questionTitle"].toString()
        }
        if (super.getProject().hasProperty("serverConfig")) {
            serverConfig = Jackson.objectMapper.readValue(
                super.getProject().properties["serverConfig"].toString(),
                ServerConfig::class.java
            )
        } else {
            serverConfig = ServerConfig.default(questionTitle)
        }
        if (super.getProject().hasProperty("generatedLanguage")) {
            generatedLanguage =
                ExtractGeneratedLanguage.extract(super.getProject().properties["generatedLanguage"].toString())
        }
        if (super.getProject().hasProperty("packageName")) {
            packageName = super.getProject().properties["packageName"].toString()
        }
        if (super.getProject().hasProperty("prefix")) {
            prefix = super.getProject().properties["prefix"].toString()
        }
        val outputDir =
            File(super.getProject().projectDir.toPath().toString() + "/src/main/" + generatedLanguage.language)
        val question = ClientInvoker.getQuestion(serverConfig)
        val langCodes: List<Pair<String?, CodeSnippetNodeTO?>>? =
            question.codeSnippets?.stream()?.map { Pair(it?.langSlug, it) }?.toList()
        val codeNode = langCodes?.find { it.first == generatedLanguage.language }
        val data = mapOf(
            Pair(Constants.CLASS_NAME, question.questionFrontendId),
            Pair(Constants.PREFIX, prefix),
            Pair(Constants.PACKAGE, packageName),
            Pair(Constants.CODE, codeNode?.second?.code),
        )
        LeetcodeFileCreator.createFile(data, outputDir, generatedLanguage)
    }
}