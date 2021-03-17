package io.github.dreamylost.invoker

import io.github.dreamylost.Constants

data class ServerConfig(
    val serverHost: String,
    val headers: Map<String, String>?,
    val questionTitle: String
) {
    companion object {
        fun default(questionTitle: String): ServerConfig {
            return ServerConfig(Constants.HOST, Constants.headers(), questionTitle)
        }
    }
}


data class ExecuteExceptionAdapter(val prefix: String, val msg: String?, val e: Throwable?) : RuntimeException(msg, e)

fun isPrimitive(entityClazzName: String?): Boolean {
    val primitiveTypes = listOf("Int", "Boolean", "String", "Short", "Byte", "Long", "Char", "Float")
    val optPrimitiveTypes = primitiveTypes.map { "$it?" }
    return primitiveTypes.contains(entityClazzName) || optPrimitiveTypes.contains(entityClazzName)
}
