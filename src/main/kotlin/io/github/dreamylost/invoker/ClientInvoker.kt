package io.github.dreamylost.invoker

import io.github.dreamylost.Constants
import io.github.graphql.resolver.QueryResolver
import io.github.graphql.model.QuestionNodeResponseProjection
import io.github.graphql.model.QuestionNodeTO
import io.github.graphql.model.QuestionQueryRequest

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