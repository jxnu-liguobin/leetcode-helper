package com.github.dreamylost.invoker

import com.github.dreamylost.Constants
import com.github.graphql.resolver.QueryResolver
import com.github.graphql.model.QuestionNodeResponseProjection
import com.github.graphql.model.QuestionNodeTO
import com.github.graphql.model.QuestionQueryRequest

object ClientInvoker {

    fun getQuestion(serverConfig: ServerConfig, questionTitle: String): QuestionNodeTO {
        val questionNodeResponseProjection = QuestionNodeResponseProjection().`all$`()
        val queryResolver = LeetcodeClient.newBuilder()
            .setConfig(
                (if (serverConfig.headers.isNullOrEmpty()) {
                    serverConfig.copy(headers = Constants.headers())
                } else serverConfig)
            )
            .setProjection(questionNodeResponseProjection).build<QueryResolver, QuestionQueryRequest>()

        return queryResolver.question(questionTitle)
    }
}