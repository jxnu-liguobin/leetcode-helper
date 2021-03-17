package io.github.graphql.model

import com.kobylynskyi.graphql.codegen.model.graphql.GraphQLRequestSerializer
import java.util.Objects
import java.util.StringJoiner

open class QuestionNodeTO(
    val questionId: String,
    val questionFrontendId: String,
    val boundTopicId: Long?,
    val title: String,
    val titleSlug: String,
    val content: String,
    val translatedTitle: String,
    val translatedContent: String,
    val isPaidOnly: Boolean,
    val difficulty: String,
    val likes: Long?,
    val dislikes: Long?,
    val isLiked: String?,
    val langToValidPlayground: org.json.JSONObject?,
    val topicTags: List<TopicTagNodeTO?>?,
    val companyTagStats: String?,
    val codeSnippets: List<CodeSnippetNodeTO?>?
) {

    companion object {
        fun builder(): Builder = Builder()
    }

    // In the future, it maybe change.
    override fun toString(): String {
        val joiner = StringJoiner(", ", "{ ", " }")
        joiner.add("questionId: " + GraphQLRequestSerializer.getEntry(questionId))
        joiner.add("questionFrontendId: " + GraphQLRequestSerializer.getEntry(questionFrontendId))
        if (boundTopicId != null) {
            joiner.add("boundTopicId: " + GraphQLRequestSerializer.getEntry(boundTopicId))
        }
        joiner.add("title: " + GraphQLRequestSerializer.getEntry(title))
        joiner.add("titleSlug: " + GraphQLRequestSerializer.getEntry(titleSlug))
        joiner.add("content: " + GraphQLRequestSerializer.getEntry(content))
        joiner.add("translatedTitle: " + GraphQLRequestSerializer.getEntry(translatedTitle))
        joiner.add("translatedContent: " + GraphQLRequestSerializer.getEntry(translatedContent))
        joiner.add("isPaidOnly: " + GraphQLRequestSerializer.getEntry(isPaidOnly))
        joiner.add("difficulty: " + GraphQLRequestSerializer.getEntry(difficulty))
        if (likes != null) {
            joiner.add("likes: " + GraphQLRequestSerializer.getEntry(likes))
        }
        if (dislikes != null) {
            joiner.add("dislikes: " + GraphQLRequestSerializer.getEntry(dislikes))
        }
        if (isLiked != null) {
            joiner.add("isLiked: " + GraphQLRequestSerializer.getEntry(isLiked))
        }
        if (langToValidPlayground != null) {
            joiner.add("langToValidPlayground: " + GraphQLRequestSerializer.getEntry(langToValidPlayground))
        }
        if (topicTags != null) {
            joiner.add("topicTags: " + GraphQLRequestSerializer.getEntry(topicTags))
        }
        if (companyTagStats != null) {
            joiner.add("companyTagStats: " + GraphQLRequestSerializer.getEntry(companyTagStats))
        }
        if (codeSnippets != null) {
            joiner.add("codeSnippets: " + GraphQLRequestSerializer.getEntry(codeSnippets))
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
        return Objects.equals(questionId, that.questionId)
                && Objects.equals(questionFrontendId, that.questionFrontendId)
                && Objects.equals(boundTopicId, that.boundTopicId)
                && Objects.equals(title, that.title)
                && Objects.equals(titleSlug, that.titleSlug)
                && Objects.equals(content, that.content)
                && Objects.equals(translatedTitle, that.translatedTitle)
                && Objects.equals(translatedContent, that.translatedContent)
                && Objects.equals(isPaidOnly, that.isPaidOnly)
                && Objects.equals(difficulty, that.difficulty)
                && Objects.equals(likes, that.likes)
                && Objects.equals(dislikes, that.dislikes)
                && Objects.equals(isLiked, that.isLiked)
                && Objects.equals(langToValidPlayground, that.langToValidPlayground)
                && Objects.equals(topicTags, that.topicTags)
                && Objects.equals(companyTagStats, that.companyTagStats)
                && Objects.equals(codeSnippets, that.codeSnippets)

    }

    override fun hashCode(): Int {
        return Objects.hash(questionId, questionFrontendId, boundTopicId, title, titleSlug, content, translatedTitle, translatedContent, isPaidOnly, difficulty, likes, dislikes, isLiked, langToValidPlayground, topicTags, companyTagStats, codeSnippets)
    }

    class Builder {

        private lateinit var questionId: String
        private lateinit var questionFrontendId: String
        private var boundTopicId: Long? = null
        private lateinit var title: String
        private lateinit var titleSlug: String
        private lateinit var content: String
        private lateinit var translatedTitle: String
        private lateinit var translatedContent: String
        private var isPaidOnly: Boolean = false
        private lateinit var difficulty: String
        private var likes: Long? = null
        private var dislikes: Long? = null
        private var isLiked: String? = null
        private var langToValidPlayground: org.json.JSONObject? = null
        private var topicTags: List<TopicTagNodeTO?>? = null
        private var companyTagStats: String? = null
        private var codeSnippets: List<CodeSnippetNodeTO?>? = null

        fun setQuestionId(questionId: String): Builder {
            this.questionId = questionId
            return this
        }

        fun setQuestionFrontendId(questionFrontendId: String): Builder {
            this.questionFrontendId = questionFrontendId
            return this
        }

        fun setBoundTopicId(boundTopicId: Long?): Builder {
            this.boundTopicId = boundTopicId
            return this
        }

        fun setTitle(title: String): Builder {
            this.title = title
            return this
        }

        fun setTitleSlug(titleSlug: String): Builder {
            this.titleSlug = titleSlug
            return this
        }

        fun setContent(content: String): Builder {
            this.content = content
            return this
        }

        fun setTranslatedTitle(translatedTitle: String): Builder {
            this.translatedTitle = translatedTitle
            return this
        }

        fun setTranslatedContent(translatedContent: String): Builder {
            this.translatedContent = translatedContent
            return this
        }

        fun setIsPaidOnly(isPaidOnly: Boolean): Builder {
            this.isPaidOnly = isPaidOnly
            return this
        }

        fun setDifficulty(difficulty: String): Builder {
            this.difficulty = difficulty
            return this
        }

        fun setLikes(likes: Long?): Builder {
            this.likes = likes
            return this
        }

        fun setDislikes(dislikes: Long?): Builder {
            this.dislikes = dislikes
            return this
        }

        fun setIsLiked(isLiked: String?): Builder {
            this.isLiked = isLiked
            return this
        }

        fun setLangToValidPlayground(langToValidPlayground: org.json.JSONObject?): Builder {
            this.langToValidPlayground = langToValidPlayground
            return this
        }

        fun setTopicTags(topicTags: List<TopicTagNodeTO?>?): Builder {
            this.topicTags = topicTags
            return this
        }

        fun setCompanyTagStats(companyTagStats: String?): Builder {
            this.companyTagStats = companyTagStats
            return this
        }

        fun setCodeSnippets(codeSnippets: List<CodeSnippetNodeTO?>?): Builder {
            this.codeSnippets = codeSnippets
            return this
        }

        fun build(): QuestionNodeTO {
            return QuestionNodeTO(questionId, questionFrontendId, boundTopicId, title, titleSlug, content, translatedTitle, translatedContent, isPaidOnly, difficulty, likes, dislikes, isLiked, langToValidPlayground, topicTags, companyTagStats, codeSnippets)
        }
    }
}
