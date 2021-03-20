package com.github.dreamylost

import com.kobylynskyi.graphql.codegen.utils.Utils
import java.io.File
import java.io.FileNotFoundException
import java.io.IOException
import java.util.*
import kotlin.test.assertEquals

object TestUtils {

    @kotlin.jvm.Throws(FileNotFoundException::class)
    fun getFileByName(files: Array<File>?, fileName: String): File {
        return Arrays.stream(files)
            .filter { f: File ->
                f.name.equals(fileName, ignoreCase = true)
            }
            .findFirst()
            .orElseThrow { FileNotFoundException() }
    }


    @Throws(IOException::class)
    fun assertSameTrimmedContent(expected: File, file: File) {
        val expectedContent = Utils.getFileContent(expected.path).trim { it <= ' ' }
        val actualContent = Utils.getFileContent(file.path).trim { it <= ' ' }
        assertEquals(expectedContent, actualContent)
    }
}