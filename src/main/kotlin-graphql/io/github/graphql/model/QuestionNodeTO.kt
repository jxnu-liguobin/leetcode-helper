package io.github.graphql.model

import com.github.dreamylost.JsonObjectDeserializer
import com.kobylynskyi.graphql.codegen.model.graphql.GraphQLRequestSerializer
import java.util.Objects
import java.util.StringJoiner

open class QuestionNodeTO(
    val questionFrontendId: String,
    val questionId: String,
    val title: String,
    val codeSnippets: List<CodeSnippetNodeTO>?,
    val topicTags: List<TopicTagNodeTO>?,
    @get:com.fasterxml.jackson.databind.annotation.JsonDeserialize(using = JsonObjectDeserializer::class)
    val metaData: org.json.JSONObject?,
    val exampleTestcases: String?,
    @get:com.fasterxml.jackson.databind.annotation.JsonDeserialize(using = JsonObjectDeserializer::class)
    val envInfo: org.json.JSONObject?
) {

    companion object {
        fun builder(): Builder = Builder()
    }

    // In the future, it maybe change.
    override fun toString(): String {
        val joiner = StringJoiner(", ", "{ ", " }")
        joiner.add("questionFrontendId: " + GraphQLRequestSerializer.getEntry(questionFrontendId))
        joiner.add("questionId: " + GraphQLRequestSerializer.getEntry(questionId))
        joiner.add("title: " + GraphQLRequestSerializer.getEntry(title))
        if (codeSnippets != null) {
            joiner.add("codeSnippets: " + GraphQLRequestSerializer.getEntry(codeSnippets))
        }
        if (topicTags != null) {
            joiner.add("topicTags: " + GraphQLRequestSerializer.getEntry(topicTags))
        }
        if (metaData != null) {
            joiner.add("metaData: " + GraphQLRequestSerializer.getEntry(metaData))
        }
        if (exampleTestcases != null) {
            joiner.add("exampleTestcases: " + GraphQLRequestSerializer.getEntry(exampleTestcases))
        }
        if (envInfo != null) {
            joiner.add("envInfo: " + GraphQLRequestSerializer.getEntry(envInfo))
        }
        return joiner.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }
        if (other == null || javaClass != other.javaClass) {
            return false
        }
        val that = other as QuestionNodeTO
        return Objects.equals(questionFrontendId, that.questionFrontendId)
                && Objects.equals(questionId, that.questionId)
                && Objects.equals(title, that.title)
                && Objects.equals(codeSnippets, that.codeSnippets)
                && Objects.equals(topicTags, that.topicTags)
                && Objects.equals(metaData, that.metaData)
                && Objects.equals(exampleTestcases, that.exampleTestcases)
                && Objects.equals(envInfo, that.envInfo)

    }

    override fun hashCode(): Int {
        return Objects.hash(questionFrontendId, questionId, title, codeSnippets, topicTags, metaData, exampleTestcases, envInfo)
    }

    class Builder {

        private lateinit var questionFrontendId: String
        private lateinit var questionId: String
        private lateinit var title: String
        private var codeSnippets: List<CodeSnippetNodeTO>? = null
        private var topicTags: List<TopicTagNodeTO>? = null
        private var metaData: org.json.JSONObject? = null
        private var exampleTestcases: String? = null
        private var envInfo: org.json.JSONObject? = null

        fun setQuestionFrontendId(questionFrontendId: String): Builder {
            this.questionFrontendId = questionFrontendId
            return this
        }

        fun setQuestionId(questionId: String): Builder {
            this.questionId = questionId
            return this
        }

        fun setTitle(title: String): Builder {
            this.title = title
            return this
        }

        fun setCodeSnippets(codeSnippets: List<CodeSnippetNodeTO>?): Builder {
            this.codeSnippets = codeSnippets
            return this
        }

        fun setTopicTags(topicTags: List<TopicTagNodeTO>?): Builder {
            this.topicTags = topicTags
            return this
        }

        fun setMetaData(metaData: org.json.JSONObject?): Builder {
            this.metaData = metaData
            return this
        }

        fun setExampleTestcases(exampleTestcases: String?): Builder {
            this.exampleTestcases = exampleTestcases
            return this
        }

        fun setEnvInfo(envInfo: org.json.JSONObject?): Builder {
            this.envInfo = envInfo
            return this
        }

        fun build(): QuestionNodeTO {
            return QuestionNodeTO(questionFrontendId, questionId, title, codeSnippets, topicTags, metaData, exampleTestcases, envInfo)
        }
    }
}
