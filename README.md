# LeetCode工具

[![Kotlin CI](https://github.com/jxnu-liguobin/leetcode-helper/actions/workflows/KotlinCI.yml/badge.svg)](https://github.com/jxnu-liguobin/leetcode-helper/actions/workflows/KotlinCI.yml)
[![Gradle Plugins](https://img.shields.io/maven-metadata/v/https/plugins.gradle.org/m2/io/github/jxnu-liguobin/leetcode-helper/maven-metadata.xml.svg?label=gradle)](https://plugins.gradle.org/plugin/io.github.jxnu-liguobin.leetcode-helper)

自动创建LeetCode文件到指定目录，为`cs-summary-reflection`编写，其他项目想使用参考`编写task`。

目前以非登录状态查询题目。

## 目录

1. [原理](#原理)
2. [使用](#使用)
   1. [命令行中使用](#命令行中使用)
   2. [Task中再封装使用](#Task中再封装使用)
   3. [参数说明](#任务参数说明)
   4. [内容示例](#内容示例)
3. [插件拓展](#插件拓展)
   1. [拓展参数说明](#拓展参数说明)
   2. [含自定义数据的内容示例](#含自定义数据的内容示例)

## 原理

根据LeetCode GraphQL API 反推Schema，使用codegen工具根据Schema生成客户端。 基于Kotlin编程实现。

## 使用

```groovy
buildscript {
    repositories {
        // 本地
        mavenLocal()
        // 远程
        maven {
            url "https://plugins.gradle.org/m2/"
        }
    }
    dependencies {
        classpath "com.github.jxnu-liguobin:leetcode-helper:0.1.2"
    }
}

apply plugin: 'com.github.jxnu-liguobin.leetcode-helper'
```

### 命令行中使用

**在多模块项目中**

```shell
# 必须指定一个模块，否则每个模块都生成一个
gradle java-examples:leetcodeCodegen  -PquestionTitle=add-two-numbers -PgeneratedLanguage=Java -PpackageName=io.github.test
```

**在单个项目中**

```shell
gradle leetcodeCodegen  -PquestionTitle=add-two-numbers -PgeneratedLanguage=Java -PpackageName=io.github.test
```

### Task中再封装使用

想在自己项目中使用的话，可以将task再包装一下，以减少参数传递：

```groovy
task leetcodeCodegenService(type: com.github.dreamylost.task.LeetcodeGradleTask) {
    questionTitle = "two-sum"// 这里都是写死的，但是在一个确定的项目中，只需要将该值设为动态读取即可，其他参数可写死，下同
    generatedLanguage = com.github.dreamylost.GeneratedLanguage.Scala
    packageName = "io.github.test"
    prefix = "Leetcode_"
    srcFolder = "src/main/scala"
    serverConfig = com.github.dreamylost.invoker.ServerConfig.defaultConfig()
}
```

### 任务参数说明

1. __questionTitle__ String，LeetCode题目的英文标题，目前没看到能根据ID查询的接口 __必填__
2. __generatedLanguage__ `GeneratedLanguage`枚举，生成的模板代码的语言，目前支持以下语言（忽略大小写）：
    - Java
    - Scala
    - Kotlin（默认）
    - Rust
3. __packageName__ String，生成的代码想要存放的目录，默认`io.github.dreamylost`
4. __serverConfig__ `ServerConfig`对象，模板服务器的配置信息（命令行执行时为JSON字符串）结构如下：
    - serverHost 目前仅支持LeetCode，默认`https://leetcode-cn.com/graphql`
    - headers 目前仅支持LeetCode，所以headers有默认值，如果默认值失效，可以自己设置
5. __prefix__ String，生成的模板代码的类名，默认`Leetcode_`，Rust会使用小写。
6. __srcFolder__ String，生成的文件的路径，默认为当前执行该插件任务所在的项目的源文件路径，如在`kotlin-leetcode`子项目中执行即为`src/main/kotlin`、在`java-leetcode`
   子项目中执行即为`src/main/java`，而rust默认为`rust-leetcode/src`

> 由于Rust不使用Gradle构建，只是存放在Gradle的项目中，所以不需要指定子项目，但须指定存放目录`srcFolder`，如`gradle leetcodeCodegen -PquestionTitle=add-two-numbers -PgeneratedLanguage=rust -PsrcFolder=rust-leetcode/src/test`

### 内容示例

暂不处理代码的格式化和依赖导入

**Kotlin**

```kotlin
package io.github.dreamylost


object Leetcode_1603 {
    class ParkingSystem(big: Int, medium: Int, small: Int) {

        fun addCar(carType: Int): Boolean {

        }

    }

    /**
     * Your ParkingSystem object will be instantiated and called as such:
     * var obj = ParkingSystem(big, medium, small)
     * var param_1 = obj.addCar(carType)
     */

    @JvmStatic
    fun main(args: Array<String>) {
        println("Hello World!")
    }
}
```

**Java**

```Java
package io.github.dreamylost;


public class Leetcode_1603 {
    class ParkingSystem {

        public ParkingSystem(int big, int medium, int small) {

        }

        public boolean addCar(int carType) {

        }
    }

    /**
     * Your ParkingSystem object will be instantiated and called as such:
     * ParkingSystem obj = new ParkingSystem(big, medium, small);
     * boolean param_1 = obj.addCar(carType);
     */

    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}
```

**Scala**

```scala
package io.github.dreamylost


object Leetcode_1603 extends App {

  class ParkingSystem(_big: Int, _medium: Int, _small: Int) {

    def addCar(carType: Int): Boolean = {

    }

  }

  /**
   * Your ParkingSystem object will be instantiated and called as such:
   * var obj = new ParkingSystem(big, medium, small)
   * var param_1 = obj.addCar(carType)
   */
}
```

**Rust**

```rust
struct ParkingSystem {}


/**
 * `&self` means the method takes an immutable reference.
 * If you need a mutable reference, change it to `&mut self` instead.
 */
impl ParkingSystem {
    fn new(big: i32, medium: i32, small: i32) -> Self {}

    fn add_car(&self, car_type: i32) -> bool {}
}

/**
 * Your ParkingSystem object will be instantiated and called as such:
 * let obj = ParkingSystem::new(big, medium, small);
 * let ret_1: bool = obj.add_car(carType);
 */
#[cfg(test)]
mod test {
    #[test]
    fn test() {
        println!("Hello world!")
    }
}
```

## 插件拓展

下面即示例项目`examples`的主要配置
```groovy
task leetcodeCodegenService(type: com.github.dreamylost.task.LeetcodeGradleTask) {
   questionTitle = "two-sum"
   generatedLanguage = com.github.dreamylost.GeneratedLanguage.Java
   packageName = "io.github.test"
   prefix = "Leetcode_"
   srcFolder = "src/main/java"
   serverConfig = com.github.dreamylost.invoker.ServerConfig.defaultConfig()
}
// 添加leetcodeExtension配置
leetcodeExtension {
   // 编写freemarker模板
    String template =
'''<#if package?has_content>
package ${package};

</#if>

public class ${prefix!""}${className!""} {
    <#if code?has_content>
    ${code}
    </#if>

    <#if customeData?has_content>
    ${customeData}
    </#if>

    public static void main(String[] args) {
        System.out.println("I am a custom template!");
    }
}
'''
    Map data = new HashMap<String, Object>()
    data.put("customeData", "public static final int x = 6;")
    customData = data
    templateName = "customTemplate"
    templateSourceCode = template
}
```

### 拓展参数说明

- __customData__ Map，自定义模板的数据
- __templateName__ String，自定义模板名称，必填
- __templateSourceCode__ String，自定义模板，必填

### 含自定义数据的内容示例

```java
package io.github.test;


public class Leetcode_1 {
    class Solution {
    public int[] twoSum(int[] nums, int target) {

    }
}

    public static final int x = 6;

    public static void main(String[] args) {
        System.out.println("I am a custom template!");
    }
}
```