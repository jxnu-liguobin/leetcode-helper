package io.github.graphql.resolver

import io.github.graphql.model.*

/**
 * Resolver for Query
 */
interface QueryResolver {

    fun question(titleSlug: String): QuestionNodeTO

}
