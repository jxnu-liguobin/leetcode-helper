package io.github.dreamylost

import io.github.dreamylost.invoker.LeetcodeException

enum class GeneratedLanguage(val language: String, val suffix: String) {
    Java("java", ".java"),
    Scala("scala", ".scala"),
    Kotlin("kotlin", ".kt"),
    Rust("rust", ".rs")
}

object ExtractGeneratedLanguage {
    fun extract(str: String): GeneratedLanguage {
        val lang = GeneratedLanguage.values().map { it.language }.find { it == str }
        return if (lang != null) {
            GeneratedLanguage.valueOf(lang.capitalize())
        } else {
            throw LeetcodeException("language not supported: ", str, null)
        }
    }
}