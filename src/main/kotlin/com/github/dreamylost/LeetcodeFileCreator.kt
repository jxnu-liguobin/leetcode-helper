/* Licensed under Apache-2.0 @梦境迷离 */
package com.github.dreamylost

import com.github.dreamylost.registry.FreeMarkerTemplatesRegistry
import com.kobylynskyi.graphql.codegen.model.DataModelFields
import com.kobylynskyi.graphql.codegen.utils.Utils
import java.io.File
import java.io.FileWriter

object LeetcodeFileCreator {

    fun createFile(
        dataModel: Map<String, Any?>,
        outputDir: File,
        generatedLanguage: GeneratedLanguage,
        deleteExistsFolder: Boolean
    ): File {
        prepareOutputDir(outputDir, deleteExistsFolder)
        val prefix = dataModel[Constants.PREFIX].toString()
        val fileName = prefix + dataModel[Constants.CLASS_NAME].toString() + generatedLanguage.suffix
        val fileOutputDir = getFileTargetDirectory(dataModel, outputDir)
        val sourceFile = File(fileOutputDir, fileName)
        try {
            if (!sourceFile.createNewFile()) {
                throw LeetcodeException("file already exists: ", sourceFile.getPath(), null)
            } else {
                println("create file: " + sourceFile.path)
            }
            val template = FreeMarkerTemplatesRegistry.getTemplateByLang(generatedLanguage)
            template.process(dataModel, FileWriter(sourceFile))
        } catch (e: Exception) {
            throw LeetcodeException("unable to create file: ", sourceFile.getPath(), e)
        }
        return sourceFile
    }

    fun prepareOutputDir(outputDir: File, deleteExistsFolder: Boolean) {
        if (deleteExistsFolder) {
            Utils.deleteDir(outputDir)
        }
        Utils.createDirIfAbsent(outputDir)
    }

    private fun getFileTargetDirectory(dataModel: Map<String, Any?>, outputDir: File): File {
        val targetDir: File
        val packageName = dataModel[DataModelFields.PACKAGE]
        targetDir =
            if (packageName != null && Utils.isNotBlank(packageName.toString())) {
                File(outputDir, packageName.toString().replace(".", File.separator))
            } else {
                outputDir
            }
        Utils.createDirIfAbsent(targetDir)
        return targetDir
    }
}
