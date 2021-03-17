package io.github.dreamylost

enum class GeneratedLanguage(val language: String, val suffix: String) {
    Java("java", ".java"),
    Scala("scala", ".scala"),
    Kotlin("kotlin", ".kt")
}

object ExtractGeneratedLanguage {
    fun extract(str: String): GeneratedLanguage {
        val lang = GeneratedLanguage.values().map { it.language }.find { it.equals(str, true) }
        return if (lang != null) {
            GeneratedLanguage.valueOf(lang.toLowerCase().capitalize())
        } else {
            GeneratedLanguage.Kotlin
        }
    }
}