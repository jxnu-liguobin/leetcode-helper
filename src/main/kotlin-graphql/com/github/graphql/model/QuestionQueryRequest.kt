/* Licensed under Apache-2.0 @梦境迷离 */
package com.github.graphql.model

import com.kobylynskyi.graphql.codegen.model.graphql.GraphQLOperation
import com.kobylynskyi.graphql.codegen.model.graphql.GraphQLOperationRequest
import java.util.Objects

open class QuestionQueryRequest(private val alias: String?) : GraphQLOperationRequest {

    companion object {
        const val OPERATION_NAME: String = "question"
        val OPERATION_TYPE: GraphQLOperation = GraphQLOperation.QUERY

        fun builder(): Builder = Builder()
    }

    private val input: MutableMap<String, Any?> = LinkedHashMap()
    private val useObjectMapperForInputSerialization: MutableSet<String> = HashSet()

    constructor() : this(null)

    fun setTitleSlug(titleSlug: String) {
        this.input["titleSlug"] = titleSlug
    }

    override fun getOperationType(): GraphQLOperation = OPERATION_TYPE

    override fun getOperationName(): String = OPERATION_NAME

    override fun getAlias(): String? = alias ?: OPERATION_NAME

    override fun getInput(): MutableMap<String, Any?> = input

    override fun getUseObjectMapperForInputSerialization(): MutableSet<String> = useObjectMapperForInputSerialization

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }
        if (other == null || javaClass != other.javaClass) {
            return false
        }
        val that = other as QuestionQueryRequest
        return Objects.equals(operationType, that.operationType) &&
            Objects.equals(operationName, that.operationName) &&
            Objects.equals(input, that.input)
    }

    override fun hashCode(): Int = Objects.hash(operationType, operationName, input)

    override fun toString(): String = Objects.toString(input)

    class Builder {

        private var `$alias`: String? = null
        private lateinit var titleSlug: String

        fun alias(alias: String?): Builder {
            this.`$alias` = alias
            return this
        }

        fun setTitleSlug(titleSlug: String): Builder {
            this.titleSlug = titleSlug
            return this
        }

        fun build(): QuestionQueryRequest {
            val obj = QuestionQueryRequest(`$alias`)
            obj.setTitleSlug(titleSlug)
            return obj
        }
    }
}
