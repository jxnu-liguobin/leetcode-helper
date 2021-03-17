package io.github.graphql.model

import com.kobylynskyi.graphql.codegen.model.graphql.GraphQLResponseField
import com.kobylynskyi.graphql.codegen.model.graphql.GraphQLResponseProjection
import java.util.Objects

/**
 * Response projection for TopicTagNode
 */
open class TopicTagNodeResponseProjection : GraphQLResponseProjection() {

    override fun `all$`(): TopicTagNodeResponseProjection = `all$`(3)

    override fun `all$`(maxDepth: Int): TopicTagNodeResponseProjection {
        this.name()
        this.slug()
        this.translatedName()
        this.typename()
        return this
    }

    fun name(): TopicTagNodeResponseProjection = name(null)

    fun name(alias: String?): TopicTagNodeResponseProjection {
        fields.add(GraphQLResponseField("name").alias(alias))
        return this
    }

    fun slug(): TopicTagNodeResponseProjection = slug(null)

    fun slug(alias: String?): TopicTagNodeResponseProjection {
        fields.add(GraphQLResponseField("slug").alias(alias))
        return this
    }

    fun translatedName(): TopicTagNodeResponseProjection = translatedName(null)

    fun translatedName(alias: String?): TopicTagNodeResponseProjection {
        fields.add(GraphQLResponseField("translatedName").alias(alias))
        return this
    }

    fun typename(): TopicTagNodeResponseProjection = typename(null)

    fun typename(alias: String?): TopicTagNodeResponseProjection {
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
        val that = other as TopicTagNodeResponseProjection
        return Objects.equals(fields, that.fields)
    }

    override fun hashCode(): Int = Objects.hash(fields)

}
