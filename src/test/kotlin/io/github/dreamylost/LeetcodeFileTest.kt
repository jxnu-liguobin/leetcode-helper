package io.github.dreamylost

import com.kobylynskyi.graphql.codegen.utils.Utils
import io.github.dreamylost.TestUtils.assertSameTrimmedContent
import io.github.dreamylost.TestUtils.getFileByName
import io.github.dreamylost.invoker.ClientInvoker
import io.github.dreamylost.invoker.ServerConfig
import io.github.graphql.model.CodeSnippetNodeTO
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.File
import java.util.*
import kotlin.streams.toList

class LeetcodeFileTest {

    private var serverConfig: ServerConfig? = null
    private val questionTitle = "design-parking-system"


    @Before
    fun init() {
        val headers = mutableMapOf<String, String>()
        headers["authority"] = "leetcode-cn.com"
        headers["user-agent"] =
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.192 Safari/537.36"
        headers["content-type"] = "application/json"
        headers["accept"] = "*/*"
        headers["x-csrftoken"] = "euEpoiETQ7z3NFQn7AqTS53QnrjxiVHeBy5MQEsLsQpg0DyPVTS7w33zt5c5RxCK"
        headers["x-definition-name"] = "https://leetcode-cn.com"
        headers["sec-fetch-site"] = "same-origin"
        headers["sec-fetch-mode"] = "cors"
        headers["sec-fetch-dest"] = "empty"
        serverConfig = ServerConfig(
            Constants.HOST,
            headers
        )
    }

    private var generatedLanguage: GeneratedLanguage = GeneratedLanguage.Kotlin
    var out: File = File("build/generated")
    var outPackage: File = File("build/generated/io/github/dreamylost")


    @After
    fun cleanup() {
        Utils.deleteDir(out)
        Utils.deleteDir(outPackage)
    }

    @Test
    fun generate_kotlin() {
        val question = ClientInvoker.getQuestion(serverConfig!!, questionTitle)
        val langCodes: List<Pair<String?, CodeSnippetNodeTO?>>? =
            question.codeSnippets?.stream()?.map { Pair(it?.langSlug, it) }?.toList()
        val codeNode = langCodes?.find { it.first == generatedLanguage.language }
        val data = mapOf(
            Pair(Constants.CLASS_NAME, question.questionFrontendId),
            Pair(Constants.CODE, codeNode?.second?.code),
            Pair(Constants.PREFIX, "Leetcode_")
        )
        LeetcodeFileCreator.createFile(data, out, generatedLanguage)
        val files = Objects.requireNonNull<Array<File>>(out.listFiles())

        getFileByName(files, "Leetcode_1603.kt").let {
            assertSameTrimmedContent(
                File("src/test/resources/expected-classes/Leetcode_1603.kt.txt"),
                it
            )
        }
    }


    @Test
    fun generate_kotlin_package() {
        val question = ClientInvoker.getQuestion(serverConfig!!, questionTitle)
        val langCodes: List<Pair<String?, CodeSnippetNodeTO?>>? =
            question.codeSnippets?.stream()?.map { Pair(it?.langSlug, it) }?.toList()
        val codeNode = langCodes?.find { it.first == generatedLanguage.language }
        val data = mapOf(
            Pair(Constants.CLASS_NAME, question.questionFrontendId),
            Pair(Constants.CODE, codeNode?.second?.code),
            Pair(Constants.PREFIX, "Leetcode_"),
            Pair(Constants.PACKAGE, "io.github.dreamylost")
        )
        LeetcodeFileCreator.createFile(data, out, generatedLanguage)
        val files = outPackage.listFiles()

        assertSameTrimmedContent(
            File("src/test/resources/expected-classes/Leetcode_1603_package.kt.txt"),
            getFileByName(files, "Leetcode_1603.kt")
        )
    }

    @Test
    fun generate_java_package() {
        generatedLanguage = GeneratedLanguage.Java
        val question = ClientInvoker.getQuestion(serverConfig!!, questionTitle)
        val langCodes: List<Pair<String?, CodeSnippetNodeTO?>>? =
            question.codeSnippets?.stream()?.map { Pair(it?.langSlug, it) }?.toList()
        val codeNode = langCodes?.find { it.first == generatedLanguage.language }
        val data = mapOf(
            Pair(Constants.CLASS_NAME, question.questionFrontendId),
            Pair(Constants.CODE, codeNode?.second?.code),
            Pair(Constants.PREFIX, "Leetcode_"),
            Pair(Constants.PACKAGE, "io.github.dreamylost")
        )
        LeetcodeFileCreator.createFile(data, out, generatedLanguage)
        val files = outPackage.listFiles()

        assertSameTrimmedContent(
            File("src/test/resources/expected-classes/Leetcode_1603_package.java.txt"),
            getFileByName(files, "Leetcode_1603.java")
        )
    }

    @Test
    fun generate_scala_package() {
        generatedLanguage = GeneratedLanguage.Scala
        val question = ClientInvoker.getQuestion(serverConfig!!, questionTitle)
        val langCodes: List<Pair<String?, CodeSnippetNodeTO?>>? =
            question.codeSnippets?.stream()?.map { Pair(it?.langSlug, it) }?.toList()
        val codeNode = langCodes?.find { it.first == generatedLanguage.language }
        val data = mapOf(
            Pair(Constants.CLASS_NAME, question.questionFrontendId),
            Pair(Constants.CODE, codeNode?.second?.code),
            Pair(Constants.PREFIX, "Leetcode_"),
            Pair(Constants.PACKAGE, "io.github.dreamylost")
        )
        LeetcodeFileCreator.createFile(data, out, generatedLanguage)
        val files = outPackage.listFiles()

        assertSameTrimmedContent(
            File("src/test/resources/expected-classes/Leetcode_1603_package.scala.txt"),
            getFileByName(files, "Leetcode_1603.scala")
        )
    }

    @Test
    fun generate_rust() {
        generatedLanguage = GeneratedLanguage.Rust
        val question = ClientInvoker.getQuestion(serverConfig!!, questionTitle)
        val langCodes: List<Pair<String?, CodeSnippetNodeTO?>>? =
            question.codeSnippets?.stream()?.map { Pair(it?.langSlug, it) }?.toList()
        val codeNode = langCodes?.find { it.first == generatedLanguage.language }
        val data = mapOf(
            Pair(Constants.CLASS_NAME, question.questionFrontendId),
            Pair(Constants.CODE, codeNode?.second?.code),
            Pair(Constants.PREFIX, "Leetcode_")
        )
        LeetcodeFileCreator.createFile(data, out, generatedLanguage)
        val files = Objects.requireNonNull<Array<File>>(out.listFiles())

        getFileByName(files, "leetcode_1603.rs").let {
            assertSameTrimmedContent(
                File("src/test/resources/expected-classes/leetcode_1603.rs.txt"),
                it
            )
        }
    }


}