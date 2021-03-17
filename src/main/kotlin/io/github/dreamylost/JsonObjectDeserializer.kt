package io.github.dreamylost

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import org.json.JSONObject

class JsonObjectDeserializer : JsonDeserializer<JSONObject>() {
    override fun deserialize(p: JsonParser?, ctxt: DeserializationContext?): JSONObject {
        return JSONObject(p?.valueAsString)
    }
}