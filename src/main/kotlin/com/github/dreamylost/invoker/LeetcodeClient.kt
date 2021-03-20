/* Licensed under Apache-2.0 @梦境迷离 */
package com.github.dreamylost.invoker

import com.kobylynskyi.graphql.codegen.model.graphql.GraphQLOperationRequest
import com.kobylynskyi.graphql.codegen.model.graphql.GraphQLResponseProjection
import java.lang.reflect.Proxy

class LeetcodeClient
constructor(
    private val projection: GraphQLResponseProjection,
    private val config: ServerConfig,
    private val resolver: Class<*>,
    private val request: Class<out GraphQLOperationRequest>
) {

    fun getResolver(): Any? {
        val invocationHandler = ResolverProxy(projection, config, request)
        return Proxy.newProxyInstance(resolver.classLoader, arrayOf(resolver), invocationHandler)
    }

    companion object {
        fun newBuilder(): GitHubKotlinClientBuilder = GitHubKotlinClientBuilder()

        class GitHubKotlinClientBuilder {

            lateinit var projection: GraphQLResponseProjection
            lateinit var config: ServerConfig

            fun setConfig(config: ServerConfig): GitHubKotlinClientBuilder {
                this.config = config
                return this
            }

            fun setProjection(projection: GraphQLResponseProjection): GitHubKotlinClientBuilder {
                this.projection = projection
                return this
            }

            inline fun <reified Resolver, reified Request : GraphQLOperationRequest> build(): Resolver {
                val invoke =
                    LeetcodeClient(this.projection, this.config, Resolver::class.java, Request::class.java)
                val resolver = invoke.getResolver()
                assert(resolver != null)
                return resolver as Resolver
            }
        }
    }
}
