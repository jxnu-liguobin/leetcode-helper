/* Licensed under Apache-2.0 @梦境迷离 */
package com.github.dreamylost.invoker

import com.github.dreamylost.Constants

data class ServerConfig(val serverHost: String, val headers: Map<String, String>) {
    companion object {
        @JvmStatic
        fun defaultConfig(): ServerConfig {
            return ServerConfig(Constants.HOST, Constants.headers())
        }
    }
}

data class ExecuteException(val prefix: String, val msg: String?, val e: Throwable?) :
    RuntimeException(msg, e)

fun isPrimitive(entityClazzName: String?): Boolean {
    val primitiveTypes = listOf("Int", "Boolean", "String", "Short", "Byte", "Long", "Char", "Float")
    val optPrimitiveTypes = primitiveTypes.map { "$it?" }
    return primitiveTypes.contains(entityClazzName) || optPrimitiveTypes.contains(entityClazzName)
}
