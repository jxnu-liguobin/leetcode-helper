<#if package?has_content>
package ${package};

</#if>

public class ${prefix!""}${className!""} {
    <#if code?has_content>
    ${code}
    </#if>

    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}