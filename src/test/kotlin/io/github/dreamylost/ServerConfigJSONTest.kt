package io.github.dreamylost

import com.fasterxml.jackson.module.kotlin.readValue
import io.github.dreamylost.invoker.Jackson
import io.github.dreamylost.invoker.ServerConfig
import org.junit.Test

class ServerConfigJSONTest {

    @Test
    fun read_json() {
        val json = """
            {
                "serverHost":"https://leetcode-cn.com/graphql",
                "headers":{
                    "authority":"leetcode-cn.com",
                    "user-agent":"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.192 Safari/537.36",
                    "content-type":"application/json",
                    "accept":"*/*",
                    "x-csrftoken":"euEpoiETQ7z3NFQn7AqTS53QnrjxiVHeBy5MQEsLsQpg0DyPVTS7w33zt5c5RxCK",
                    "x-definition-name":"https://leetcode-cn.com",
                    "sec-fetch-site":"same-origin",
                    "sec-fetch-mode":"cors",
                    "sec-fetch-dest":"empty"
                }
            }
        """.trimIndent()

        val serverConfig = Jackson.objectMapper.readValue<ServerConfig>(json)
        assert(ServerConfig.defaultConfig() == serverConfig)

    }
}