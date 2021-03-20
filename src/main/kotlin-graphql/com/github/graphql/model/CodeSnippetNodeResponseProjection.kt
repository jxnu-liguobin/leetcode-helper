/* Licensed under Apache-2.0 @梦境迷离 */
package com.github.graphql.model

import com.kobylynskyi.graphql.codegen.model.graphql.GraphQLResponseField
import com.kobylynskyi.graphql.codegen.model.graphql.GraphQLResponseProjection
import java.util.Objects

/**
 * Response projection for CodeSnippetNode
 */
open class CodeSnippetNodeResponseProjection : GraphQLResponseProjection() {

    override fun `all$`(): CodeSnippetNodeResponseProjection = `all$`(3)

    override fun `all$`(maxDepth: Int): CodeSnippetNodeResponseProjection {
        this.lang()
        this.langSlug()
        this.code()
        this.typename()
        return this
    }

    fun lang(): CodeSnippetNodeResponseProjection = lang(null)

    fun lang(alias: String?): CodeSnippetNodeResponseProjection {
        fields.add(GraphQLResponseField("lang").alias(alias))
        return this
    }

    fun langSlug(): CodeSnippetNodeResponseProjection = langSlug(null)

    fun langSlug(alias: String?): CodeSnippetNodeResponseProjection {
        fields.add(GraphQLResponseField("langSlug").alias(alias))
        return this
    }

    fun code(): CodeSnippetNodeResponseProjection = code(null)

    fun code(alias: String?): CodeSnippetNodeResponseProjection {
        fields.add(GraphQLResponseField("code").alias(alias))
        return this
    }

    fun typename(): CodeSnippetNodeResponseProjection = typename(null)

    fun typename(alias: String?): CodeSnippetNodeResponseProjection {
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
        val that = other as CodeSnippetNodeResponseProjection
        return Objects.equals(fields, that.fields)
    }

    override fun hashCode(): Int = Objects.hash(fields)
}
