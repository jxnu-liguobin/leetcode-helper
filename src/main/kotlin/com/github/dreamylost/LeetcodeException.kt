/* Licensed under Apache-2.0 @梦境迷离 */
package com.github.dreamylost

data class LeetcodeException(val prefix: String, val msg: String?, val e: Throwable?) :
    RuntimeException(msg, e)
