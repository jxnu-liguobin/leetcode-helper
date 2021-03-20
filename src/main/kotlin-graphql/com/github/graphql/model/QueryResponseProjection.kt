/* Licensed under Apache-2.0 @梦境迷离 */
package com.github.graphql.model

import com.kobylynskyi.graphql.codegen.model.graphql.GraphQLResponseField
import com.kobylynskyi.graphql.codegen.model.graphql.GraphQLResponseProjection
import java.util.Objects

/**
 * Response projection for Query
 */
open class QueryResponseProjection : GraphQLResponseProjection() {

    override fun `all$`(): QueryResponseProjection = `all$`(3)

    override fun `all$`(maxDepth: Int): QueryResponseProjection {
        if (projectionDepthOnFields.getOrDefault("QueryResponseProjection.QuestionNodeResponseProjection.question", 0) <= maxDepth) {
            projectionDepthOnFields["QueryResponseProjection.QuestionNodeResponseProjection.question"] = projectionDepthOnFields.getOrDefault("QueryResponseProjection.QuestionNodeResponseProjection.question", 0) + 1
            this.question(QuestionNodeResponseProjection().`all$`(maxDepth - projectionDepthOnFields.getOrDefault("QueryResponseProjection.QuestionNodeResponseProjection.question", 0)))
        }
        this.typename()
        return this
    }

    fun question(subProjection: QuestionNodeResponseProjection): QueryResponseProjection = question(null, subProjection)

    fun question(alias: String?, subProjection: QuestionNodeResponseProjection): QueryResponseProjection {
        fields.add(GraphQLResponseField("question").alias(alias).projection(subProjection))
        return this
    }

    fun question(input: QueryQuestionParametrizedInput, subProjection: QuestionNodeResponseProjection): QueryResponseProjection = question(null, input, subProjection)

    fun question(alias: String?, input: QueryQuestionParametrizedInput, subProjection: QuestionNodeResponseProjection): QueryResponseProjection {
        fields.add(GraphQLResponseField("question").alias(alias).parameters(input).projection(subProjection))
        return this
    }

    fun typename(): QueryResponseProjection = typename(null)

    fun typename(alias: String?): QueryResponseProjection {
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
        val that = other as QueryResponseProjection
        return Objects.equals(fields, that.fields)
    }

    override fun hashCode(): Int = Objects.hash(fields)
}
