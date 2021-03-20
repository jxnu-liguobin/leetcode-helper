/* Licensed under Apache-2.0 @梦境迷离 */
package com.github.graphql.model

import com.kobylynskyi.graphql.codegen.model.graphql.GraphQLResult

open class QuestionQueryResponse : GraphQLResult<MutableMap<String, QuestionNodeTO>>() {

    companion object {
        const val OPERATION_NAME: String = "question"
    }

    fun question(): QuestionNodeTO {
        val data: MutableMap<String, QuestionNodeTO> = super.getData()
        return data.getValue(OPERATION_NAME)
    }
}
