<#if package?has_content>
package ${package}

</#if>

object ${prefix}${className} extends App {
    <#if code?has_content>
    ${code}
    </#if>
}