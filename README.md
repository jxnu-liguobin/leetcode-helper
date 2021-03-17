# LeetCode工具

自动创建LeetCode文件到指定目录，为`cs-summary-reflection`编写，其他项目想使用参考`编写task`

## 原理

根据LeetCode GraphQL API 反推Schema，使用codegen工具根据Schema生成客户端。 基于Kotlin编程实现。

## 使用

**在多模块项目中**
```shell
# 必须指定一个模块，否则每个模块都生成一个
 gradle java-examples:leetcodeCodegen  -PquestionTitle=add-two-numbers -PgeneratedLanguage=Java -PpackageName=io.github.test
```

**在单个项目中**
```shell
 gradle leetcodeCodegen  -PquestionTitle=add-two-numbers -PgeneratedLanguage=Java -PpackageName=io.github.test
```

## 参数说明

1. questionTitle LeetCode题目的因为标题，目前好像只能根据标题搜索，__必填__
2. generatedLanguage 生成的模板代码的语言，目前支持以下语言（忽略大小写，默认Kotlin）：
    - Java
    - Scala
    - Kotlin
3. packageName 生成的代码想要存放的目录，默认`io.github.dreamylost`
4. serverConfig 模板服务器的配置信息（JSON字符串），目前serverHost仅支持LeetCode，结构如下：
```kotlin
data class ServerConfig(
    val serverHost: String,
    val headers: Map<String, String>?,
    val questionTitle: String
) 
```
5. prefix 生成的模板代码的类名，默认`Leetcode_`

## 模板

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

## 编写task

在自己项目中使用的话，将task再包装一下：
```groovy
    task leetcodeCodegenService(type: io.github.dreamylost.task.LeetcodeGradleTask) {
   questionTitle = hasProperty('questionTitle') ? properties['questionTitle'] : "two-sum"
   generatedLanguage = hasProperty('generatedLanguage') ? properties['generatedLanguage'] : "java"
   packageName = hasProperty('packageName') ? properties['packageName'] : "io.github.test"
   prefix = hasProperty('packageName') ? properties['packageName'] : "Leetcode_"
}
```

**在单个项目中**
```shell
gradle leetcodeCodegenService
```

**在多模块项目中**
```shell
gradle moduleName:leetcodeCodegenService
```