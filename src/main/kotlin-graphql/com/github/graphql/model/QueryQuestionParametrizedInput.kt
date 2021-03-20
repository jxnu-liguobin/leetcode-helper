/* Licensed under Apache-2.0 @梦境迷离 */
package com.github.graphql.model

import com.kobylynskyi.graphql.codegen.model.graphql.GraphQLParametrizedInput
import com.kobylynskyi.graphql.codegen.model.graphql.GraphQLRequestSerializer
import java.util.StringJoiner
/**
 * Parametrized input for field question in type Query
 */
data class QueryQuestionParametrizedInput(
    val titleSlug: String
) : GraphQLParametrizedInput {

    override fun toString(): String {
        val joiner = StringJoiner(", ", "( ", " )")
        joiner.add("titleSlug: " + GraphQLRequestSerializer.getEntry(titleSlug))
        return joiner.toString()
    }
}
