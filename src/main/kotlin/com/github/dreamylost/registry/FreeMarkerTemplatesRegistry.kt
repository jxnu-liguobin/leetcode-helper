package com.github.dreamylost.registry

import freemarker.ext.beans.BeansWrapper
import freemarker.template.Configuration
import freemarker.template.Template
import freemarker.template.TemplateExceptionHandler
import com.github.dreamylost.GeneratedLanguage
import com.github.dreamylost.LeetcodeGradlePlugin
import java.util.*


object FreeMarkerTemplatesRegistry {

    private val FREEMARKER_TEMPLATE_VERSION: freemarker.template.Version = Configuration.VERSION_2_3_30
    private val templateMap =
        EnumMap<GeneratedLanguage, Template>(GeneratedLanguage::class.java)

    init {
        val configuration = getConfiguration()
        configuration.setClassLoaderForTemplateLoading(LeetcodeGradlePlugin::class.java.classLoader, "")
        templateMap[GeneratedLanguage.Kotlin] = configuration.getTemplate("templates/KotlinTemplate.ftl")
        templateMap[GeneratedLanguage.Scala] = configuration.getTemplate("templates/ScalaTemplate.ftl")
        templateMap[GeneratedLanguage.Java] = configuration.getTemplate("templates/JavaTemplate.ftl")
        templateMap[GeneratedLanguage.Rust] = configuration.getTemplate("templates/RustTemplate.ftl")

    }

    private fun getConfiguration(): Configuration {
        val beansWrapper = BeansWrapper(FREEMARKER_TEMPLATE_VERSION)
        val configuration = Configuration(FREEMARKER_TEMPLATE_VERSION)
        configuration.defaultEncoding = "UTF-8"
        configuration.templateExceptionHandler = TemplateExceptionHandler.RETHROW_HANDLER
        configuration.logTemplateExceptions = false
        configuration.wrapUncheckedExceptions = true
        configuration.setSharedVariable("statics", beansWrapper.staticModels)
        return configuration
    }

    private fun getTemplateMap(): EnumMap<GeneratedLanguage, Template> {
        return templateMap
    }

    fun customRegister(templateName: String, templateSourceCode: String, generatedLanguage: GeneratedLanguage) {
        val configuration = getConfiguration()
        val template = Template(templateName, templateSourceCode, configuration)
        getTemplateMap()[generatedLanguage] = template
    }

    fun getTemplateByLang(generatedLanguage: GeneratedLanguage): Template {
        return templateMap[generatedLanguage]!!
    }

}