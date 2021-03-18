package io.github.dreamylost.registry

import com.kobylynskyi.graphql.codegen.GraphQLCodegen
import freemarker.ext.beans.BeansWrapper
import freemarker.template.Configuration
import freemarker.template.Template
import freemarker.template.TemplateExceptionHandler
import io.github.dreamylost.GeneratedLanguage
import java.util.*


object FreeMarkerTemplatesRegistry {

    private val FREEMARKER_TEMPLATE_VERSION: freemarker.template.Version = Configuration.VERSION_2_3_30
    private val templateMap =
        EnumMap<GeneratedLanguage, Template>(GeneratedLanguage::class.java)

    init {
        val beansWrapper = BeansWrapper(FREEMARKER_TEMPLATE_VERSION)
        val configuration = Configuration(FREEMARKER_TEMPLATE_VERSION)
        configuration.setClassLoaderForTemplateLoading(GraphQLCodegen::class.java.classLoader, "")
        configuration.defaultEncoding = "UTF-8"
        configuration.templateExceptionHandler = TemplateExceptionHandler.RETHROW_HANDLER
        configuration.logTemplateExceptions = false
        configuration.wrapUncheckedExceptions = true
        configuration.setSharedVariable("statics", beansWrapper.staticModels)
        templateMap[GeneratedLanguage.Kotlin] = configuration.getTemplate("templates/KotlinTemplate.ftl")
        templateMap[GeneratedLanguage.Scala] = configuration.getTemplate("templates/ScalaTemplate.ftl")
        templateMap[GeneratedLanguage.Java] = configuration.getTemplate("templates/JavaTemplate.ftl")
        templateMap[GeneratedLanguage.Rust] = configuration.getTemplate("templates/RustTemplate.ftl")

    }


    fun getTemplateByLang(generatedLanguage: GeneratedLanguage): Template {
        return templateMap[generatedLanguage]!!
    }

}