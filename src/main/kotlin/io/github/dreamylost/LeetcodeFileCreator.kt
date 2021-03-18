package io.github.dreamylost

import com.kobylynskyi.graphql.codegen.model.DataModelFields
import com.kobylynskyi.graphql.codegen.model.exception.UnableToCreateFileException
import com.kobylynskyi.graphql.codegen.utils.Utils
import io.github.dreamylost.registry.FreeMarkerTemplatesRegistry
import java.io.File
import java.io.FileWriter

object LeetcodeFileCreator {

    fun createFile(
        dataModel: Map<String, Any?>,
        outputDir: File,
        generatedLanguage: GeneratedLanguage
    ): File {
        val prefix = dataModel[Constants.PREFIX].toString()
        val fileName = prefix + dataModel[Constants.CLASS_NAME].toString() + generatedLanguage.suffix
        val fileOutputDir = getFileTargetDirectory(dataModel, outputDir)
        val sourceFile = File(fileOutputDir, fileName)
        try {
            if (sourceFile.exists()) {
                println("file exists: " + sourceFile.path)
                sourceFile.delete()
            } else {
                println("create File: $sourceFile")
            }
            val template = FreeMarkerTemplatesRegistry.getTemplateByLang(generatedLanguage)
            template.process(dataModel, FileWriter(sourceFile))
        } catch (e: Exception) {
            throw UnableToCreateFileException(e)
        }
        return sourceFile

    }

    private fun getFileTargetDirectory(dataModel: Map<String, Any?>, outputDir: File): File {
        val targetDir: File
        val packageName = dataModel[DataModelFields.PACKAGE]
        targetDir = if (packageName != null && Utils.isNotBlank(packageName.toString())) {
            File(outputDir, packageName.toString().replace(".", File.separator))
        } else {
            outputDir
        }
        Utils.createDirIfAbsent(targetDir)
        return targetDir
    }
}