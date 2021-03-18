<#if package?has_content>
package ${package}

</#if>

object ${prefix!""}${className!""} {
    <#if code?has_content>
    ${code}
    </#if>

    @JvmStatic
    fun main(args: Array<String>) {
      println("Hello World!")
    }
}