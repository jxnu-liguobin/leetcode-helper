package io.github.graphql.model

import com.kobylynskyi.graphql.codegen.model.graphql.GraphQLRequestSerializer
import java.util.Objects
import java.util.StringJoiner

open class CodeSnippetNodeTO(
    val lang: String,
    val langSlug: String,
    val code: String
) {

    companion object {
        fun builder(): Builder = Builder()
    }

    // In the future, it maybe change.
    override fun toString(): String {
        val joiner = StringJoiner(", ", "{ ", " }")
        joiner.add("lang: " + GraphQLRequestSerializer.getEntry(lang))
        joiner.add("langSlug: " + GraphQLRequestSerializer.getEntry(langSlug))
        joiner.add("code: " + GraphQLRequestSerializer.getEntry(code))
        return joiner.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }
        if (other == null || javaClass != other.javaClass) {
            return false
        }
        val that = other as CodeSnippetNodeTO
        return Objects.equals(lang, that.lang)
                && Objects.equals(langSlug, that.langSlug)
                && Objects.equals(code, that.code)

    }

    override fun hashCode(): Int {
        return Objects.hash(lang, langSlug, code)
    }

    class Builder {

        private lateinit var lang: String
        private lateinit var langSlug: String
        private lateinit var code: String

        fun setLang(lang: String): Builder {
            this.lang = lang
            return this
        }

        fun setLangSlug(langSlug: String): Builder {
            this.langSlug = langSlug
            return this
        }

        fun setCode(code: String): Builder {
            this.code = code
            return this
        }

        fun build(): CodeSnippetNodeTO {
            return CodeSnippetNodeTO(lang, langSlug, code)
        }
    }
}
