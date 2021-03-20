/* Licensed under Apache-2.0 @梦境迷离 */
package com.github.graphql.resolver

import com.github.graphql.model.*

/**
 * Resolver for Query
 */
interface QueryResolver {

    fun question(titleSlug: String): QuestionNodeTO
}
