package io.github.graphql.model

import com.kobylynskyi.graphql.codegen.model.graphql.GraphQLResponseField
import com.kobylynskyi.graphql.codegen.model.graphql.GraphQLResponseProjection
import java.util.Objects

/**
 * Response projection for QuestionNode
 */
open class QuestionNodeResponseProjection : GraphQLResponseProjection() {

    override fun `all$`(): QuestionNodeResponseProjection = `all$`(3)

    override fun `all$`(maxDepth: Int): QuestionNodeResponseProjection {
        this.questionId()
        this.questionFrontendId()
        this.boundTopicId()
        this.title()
        this.titleSlug()
        this.content()
        this.translatedTitle()
        this.translatedContent()
        this.isPaidOnly()
        this.difficulty()
        this.likes()
        this.dislikes()
        this.isLiked()
        this.langToValidPlayground()
        if (projectionDepthOnFields.getOrDefault("QuestionNodeResponseProjection.TopicTagNodeResponseProjection.topicTags", 0) <= maxDepth) {
            projectionDepthOnFields["QuestionNodeResponseProjection.TopicTagNodeResponseProjection.topicTags"] = projectionDepthOnFields.getOrDefault("QuestionNodeResponseProjection.TopicTagNodeResponseProjection.topicTags", 0) + 1
            this.topicTags(TopicTagNodeResponseProjection().`all$`(maxDepth - projectionDepthOnFields.getOrDefault("QuestionNodeResponseProjection.TopicTagNodeResponseProjection.topicTags", 0)))
        }
        this.companyTagStats()
        if (projectionDepthOnFields.getOrDefault("QuestionNodeResponseProjection.CodeSnippetNodeResponseProjection.codeSnippets", 0) <= maxDepth) {
            projectionDepthOnFields["QuestionNodeResponseProjection.CodeSnippetNodeResponseProjection.codeSnippets"] = projectionDepthOnFields.getOrDefault("QuestionNodeResponseProjection.CodeSnippetNodeResponseProjection.codeSnippets", 0) + 1
            this.codeSnippets(CodeSnippetNodeResponseProjection().`all$`(maxDepth - projectionDepthOnFields.getOrDefault("QuestionNodeResponseProjection.CodeSnippetNodeResponseProjection.codeSnippets", 0)))
        }
        this.typename()
        return this
    }

    fun questionId(): QuestionNodeResponseProjection = questionId(null)

    fun questionId(alias: String?): QuestionNodeResponseProjection {
        fields.add(GraphQLResponseField("questionId").alias(alias))
        return this
    }

    fun questionFrontendId(): QuestionNodeResponseProjection = questionFrontendId(null)

    fun questionFrontendId(alias: String?): QuestionNodeResponseProjection {
        fields.add(GraphQLResponseField("questionFrontendId").alias(alias))
        return this
    }

    fun boundTopicId(): QuestionNodeResponseProjection = boundTopicId(null)

    fun boundTopicId(alias: String?): QuestionNodeResponseProjection {
        fields.add(GraphQLResponseField("boundTopicId").alias(alias))
        return this
    }

    fun title(): QuestionNodeResponseProjection = title(null)

    fun title(alias: String?): QuestionNodeResponseProjection {
        fields.add(GraphQLResponseField("title").alias(alias))
        return this
    }

    fun titleSlug(): QuestionNodeResponseProjection = titleSlug(null)

    fun titleSlug(alias: String?): QuestionNodeResponseProjection {
        fields.add(GraphQLResponseField("titleSlug").alias(alias))
        return this
    }

    fun content(): QuestionNodeResponseProjection = content(null)

    fun content(alias: String?): QuestionNodeResponseProjection {
        fields.add(GraphQLResponseField("content").alias(alias))
        return this
    }

    fun translatedTitle(): QuestionNodeResponseProjection = translatedTitle(null)

    fun translatedTitle(alias: String?): QuestionNodeResponseProjection {
        fields.add(GraphQLResponseField("translatedTitle").alias(alias))
        return this
    }

    fun translatedContent(): QuestionNodeResponseProjection = translatedContent(null)

    fun translatedContent(alias: String?): QuestionNodeResponseProjection {
        fields.add(GraphQLResponseField("translatedContent").alias(alias))
        return this
    }

    fun isPaidOnly(): QuestionNodeResponseProjection = isPaidOnly(null)

    fun isPaidOnly(alias: String?): QuestionNodeResponseProjection {
        fields.add(GraphQLResponseField("isPaidOnly").alias(alias))
        return this
    }

    fun difficulty(): QuestionNodeResponseProjection = difficulty(null)

    fun difficulty(alias: String?): QuestionNodeResponseProjection {
        fields.add(GraphQLResponseField("difficulty").alias(alias))
        return this
    }

    fun likes(): QuestionNodeResponseProjection = likes(null)

    fun likes(alias: String?): QuestionNodeResponseProjection {
        fields.add(GraphQLResponseField("likes").alias(alias))
        return this
    }

    fun dislikes(): QuestionNodeResponseProjection = dislikes(null)

    fun dislikes(alias: String?): QuestionNodeResponseProjection {
        fields.add(GraphQLResponseField("dislikes").alias(alias))
        return this
    }

    fun isLiked(): QuestionNodeResponseProjection = isLiked(null)

    fun isLiked(alias: String?): QuestionNodeResponseProjection {
        fields.add(GraphQLResponseField("isLiked").alias(alias))
        return this
    }

    fun langToValidPlayground(): QuestionNodeResponseProjection = langToValidPlayground(null)

    fun langToValidPlayground(alias: String?): QuestionNodeResponseProjection {
        fields.add(GraphQLResponseField("langToValidPlayground").alias(alias))
        return this
    }

    fun topicTags(subProjection: TopicTagNodeResponseProjection): QuestionNodeResponseProjection = topicTags(null, subProjection)

    fun topicTags(alias: String?, subProjection: TopicTagNodeResponseProjection): QuestionNodeResponseProjection {
        fields.add(GraphQLResponseField("topicTags").alias(alias).projection(subProjection))
        return this
    }

    fun companyTagStats(): QuestionNodeResponseProjection = companyTagStats(null)

    fun companyTagStats(alias: String?): QuestionNodeResponseProjection {
        fields.add(GraphQLResponseField("companyTagStats").alias(alias))
        return this
    }

    fun codeSnippets(subProjection: CodeSnippetNodeResponseProjection): QuestionNodeResponseProjection = codeSnippets(null, subProjection)

    fun codeSnippets(alias: String?, subProjection: CodeSnippetNodeResponseProjection): QuestionNodeResponseProjection {
        fields.add(GraphQLResponseField("codeSnippets").alias(alias).projection(subProjection))
        return this
    }

    fun typename(): QuestionNodeResponseProjection = typename(null)

    fun typename(alias: String?): QuestionNodeResponseProjection {
        fields.add(GraphQLResponseField("__typename").alias(alias))
        return this
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }
        if (other == null || javaClass != other.javaClass) {
            return false
        }
        val that = other as QuestionNodeResponseProjection
        return Objects.equals(fields, that.fields)
    }

    override fun hashCode(): Int = Objects.hash(fields)

}
