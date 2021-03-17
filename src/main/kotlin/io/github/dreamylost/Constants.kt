package io.github.dreamylost

object Constants {

    const val CLASS_NAME = "className"
    const val CODE = "code"
    const val PACKAGE = "package"
    const val PREFIX = "prefix"
    const val HOST = "https://leetcode-cn.com/graphql"

    fun headers(): MutableMap<String, String> {
        val headers = mutableMapOf<String, String>()
        headers["authority"] = "leetcode-cn.com"
        headers["user-agent"] =
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.192 Safari/537.36"
        headers["content-type"] = "application/json"
        headers["accept"] = "*/*"
        headers["x-csrftoken"] = "euEpoiETQ7z3NFQn7AqTS53QnrjxiVHeBy5MQEsLsQpg0DyPVTS7w33zt5c5RxCK"
        headers["x-definition-name"] = "https://leetcode-cn.com"
        headers["sec-fetch-site"] = "same-origin"
        headers["sec-fetch-mode"] = "cors"
        headers["sec-fetch-dest"] = "empty"
        return headers
    }

}