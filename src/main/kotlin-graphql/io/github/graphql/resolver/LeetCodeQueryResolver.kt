package io.github.graphql.resolver

import io.github.graphql.model.*

interface LeetCodeQueryResolver {

    fun question(titleSlug: String): QuestionNodeTO

}
