package io.github.graphql.model

import com.kobylynskyi.graphql.codegen.model.graphql.GraphQLRequestSerializer
import java.util.Objects
import java.util.StringJoiner

open class TopicTagNodeTO(
    val name: String?,
    val slug: String?,
    val translatedName: String?
) {

    companion object {
        fun builder(): Builder = Builder()
    }

    // In the future, it maybe change.
    override fun toString(): String {
        val joiner = StringJoiner(", ", "{ ", " }")
        if (name != null) {
            joiner.add("name: " + GraphQLRequestSerializer.getEntry(name))
        }
        if (slug != null) {
            joiner.add("slug: " + GraphQLRequestSerializer.getEntry(slug))
        }
        if (translatedName != null) {
            joiner.add("translatedName: " + GraphQLRequestSerializer.getEntry(translatedName))
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
        val that = other as TopicTagNodeTO
        return Objects.equals(name, that.name)
                && Objects.equals(slug, that.slug)
                && Objects.equals(translatedName, that.translatedName)

    }

    override fun hashCode(): Int {
        return Objects.hash(name, slug, translatedName)
    }

    class Builder {

        private var name: String? = null
        private var slug: String? = null
        private var translatedName: String? = null

        fun setName(name: String?): Builder {
            this.name = name
            return this
        }

        fun setSlug(slug: String?): Builder {
            this.slug = slug
            return this
        }

        fun setTranslatedName(translatedName: String?): Builder {
            this.translatedName = translatedName
            return this
        }

        fun build(): TopicTagNodeTO {
            return TopicTagNodeTO(name, slug, translatedName)
        }
    }
}
