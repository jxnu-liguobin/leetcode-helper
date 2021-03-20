/* Licensed under Apache-2.0 @梦境迷离 */
package com.github.dreamylost.invoker

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jsonMapper
import com.fasterxml.jackson.module.kotlin.kotlinModule
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule
import com.kobylynskyi.graphql.codegen.model.graphql.GraphQLRequest
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject
import java.util.concurrent.TimeUnit

object OkHttp {

    private const val ApplicationJson = "application/json; charset=utf-8"
    private val json: MediaType? = ApplicationJson.toMediaTypeOrNull()
    private val defaultTimeout: Long = TimeUnit.MINUTES.toMillis(1)
    private val client: OkHttpClient = buildClient()

    private fun buildClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(defaultTimeout, TimeUnit.MILLISECONDS)
            .writeTimeout(defaultTimeout, TimeUnit.MILLISECONDS)
            .connectTimeout(defaultTimeout, TimeUnit.MILLISECONDS)
            .protocols(listOf(Protocol.HTTP_1_1, Protocol.HTTP_2))
            .build()
    }

    private fun buildRequest(config: ServerConfig, request: GraphQLRequest): Request.Builder {
        val httpRequestBody = request.toHttpJsonBody()
        println("host: ${config.serverHost}")
        println("headers: ${config.headers}")
        println("graphql request body: $httpRequestBody")
        val rb =
            Request.Builder()
                .url(config.serverHost)
                .addHeader("Accept", ApplicationJson)
                .post(httpRequestBody.toRequestBody(json))
        config.headers.forEach { run { rb.addHeader(it.key, it.value) } }
        return rb
    }

    fun syncRunQuery(config: ServerConfig, entityClassName: String, request: GraphQLRequest): Any? {
        val rb = buildRequest(config, request)
        val response = client.newCall(rb.build()).execute()
        if (response.isSuccessful) {
            val jsonObject = JSONObject(response.body?.string())
            if (!jsonObject.isNull("errors")) {
                throw ExecuteException(
                    "found errors in response: ", jsonObject.get("errors").toString(), null
                )
            } else {
                val dataJSON = jsonObject.getJSONObject("data")
                val data = dataJSON.get(request.request.operationName)
                println("result: $data")
                return deserialized(data, entityClassName)
            }
        } else {
            throw ExecuteException("response is fail", response.body?.string(), null)
        }
    }

    // TODO optimize it.
    private fun deserialized(data: Any?, entityClazzName: String): Any? {
        if (data == null) return null
        if (isPrimitive(entityClazzName)) return data
        val result = mutableListOf<Any?>()
        val targetClass = Class.forName(entityClazzName)
        return try {
            if (data is JSONArray) {
                for (i in 0 until data.length()) {
                    val e =
                        Jackson.objectMapper.readValue((data.get(i) as JSONObject).toString(), targetClass)
                    result.add(e)
                }
                result
            } else {
                Jackson.objectMapper.readValue((data as JSONObject).toString(), targetClass)
            }
        } catch (e: Exception) {
            throw ExecuteException("deserialize data failed: ", e.localizedMessage, e)
        }
    }
}

// Pseudo adaptation, avoiding the interdependence of three languages, using independent
// implementation.
object Jackson {

    val objectMapper = jsonMapper {
        serializationInclusion(JsonInclude.Include.NON_NULL)
        serializationInclusion(JsonInclude.Include.NON_ABSENT)
        configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        addModule(ParameterNamesModule())
        addModule(Jdk8Module())
        addModule(JavaTimeModule())
        addModule(kotlinModule())
        configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
    }
}
