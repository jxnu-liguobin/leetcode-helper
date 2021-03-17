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
        this.questionFrontendId()
        this.questionId()
        this.title()
        if (projectionDepthOnFields.getOrDefault("QuestionNodeResponseProjection.CodeSnippetNodeResponseProjection.codeSnippets", 0) <= maxDepth) {
            projectionDepthOnFields["QuestionNodeResponseProjection.CodeSnippetNodeResponseProjection.codeSnippets"] = projectionDepthOnFields.getOrDefault("QuestionNodeResponseProjection.CodeSnippetNodeResponseProjection.codeSnippets", 0) + 1
            this.codeSnippets(CodeSnippetNodeResponseProjection().`all$`(maxDepth - projectionDepthOnFields.getOrDefault("QuestionNodeResponseProjection.CodeSnippetNodeResponseProjection.codeSnippets", 0)))
        }
        if (projectionDepthOnFields.getOrDefault("QuestionNodeResponseProjection.TopicTagNodeResponseProjection.topicTags", 0) <= maxDepth) {
            projectionDepthOnFields["QuestionNodeResponseProjection.TopicTagNodeResponseProjection.topicTags"] = projectionDepthOnFields.getOrDefault("QuestionNodeResponseProjection.TopicTagNodeResponseProjection.topicTags", 0) + 1
            this.topicTags(TopicTagNodeResponseProjection().`all$`(maxDepth - projectionDepthOnFields.getOrDefault("QuestionNodeResponseProjection.TopicTagNodeResponseProjection.topicTags", 0)))
        }
        this.metaData()
        this.exampleTestcases()
        this.envInfo()
        this.typename()
        return this
    }

    fun questionFrontendId(): QuestionNodeResponseProjection = questionFrontendId(null)

    fun questionFrontendId(alias: String?): QuestionNodeResponseProjection {
        fields.add(GraphQLResponseField("questionFrontendId").alias(alias))
        return this
    }

    fun questionId(): QuestionNodeResponseProjection = questionId(null)

    fun questionId(alias: String?): QuestionNodeResponseProjection {
        fields.add(GraphQLResponseField("questionId").alias(alias))
        return this
    }

    fun title(): QuestionNodeResponseProjection = title(null)

    fun title(alias: String?): QuestionNodeResponseProjection {
        fields.add(GraphQLResponseField("title").alias(alias))
        return this
    }

    fun codeSnippets(subProjection: CodeSnippetNodeResponseProjection): QuestionNodeResponseProjection = codeSnippets(null, subProjection)

    fun codeSnippets(alias: String?, subProjection: CodeSnippetNodeResponseProjection): QuestionNodeResponseProjection {
        fields.add(GraphQLResponseField("codeSnippets").alias(alias).projection(subProjection))
        return this
    }

    fun topicTags(subProjection: TopicTagNodeResponseProjection): QuestionNodeResponseProjection = topicTags(null, subProjection)

    fun topicTags(alias: String?, subProjection: TopicTagNodeResponseProjection): QuestionNodeResponseProjection {
        fields.add(GraphQLResponseField("topicTags").alias(alias).projection(subProjection))
        return this
    }

    fun metaData(): QuestionNodeResponseProjection = metaData(null)

    fun metaData(alias: String?): QuestionNodeResponseProjection {
        fields.add(GraphQLResponseField("metaData").alias(alias))
        return this
    }

    fun exampleTestcases(): QuestionNodeResponseProjection = exampleTestcases(null)

    fun exampleTestcases(alias: String?): QuestionNodeResponseProjection {
        fields.add(GraphQLResponseField("exampleTestcases").alias(alias))
        return this
    }

    fun envInfo(): QuestionNodeResponseProjection = envInfo(null)

    fun envInfo(alias: String?): QuestionNodeResponseProjection {
        fields.add(GraphQLResponseField("envInfo").alias(alias))
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
