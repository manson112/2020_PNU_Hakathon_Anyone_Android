package pnu.hakathon.anyone.network

import com.google.gson.JsonArray
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import timber.log.Timber
import java.lang.reflect.Type

class ServerResponseDeserializer : JsonDeserializer<ServerResponse> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): ServerResponse {
        val item = ServerResponse()
        json?.let {
            val jsonObject = it.asJsonObject

            val resultCode = jsonObject.get("code").asInt
            val message = jsonObject.get("message").asString
            var dataList: JsonArray? = null
            Timber.d(json.toString())
            if (!jsonObject.get("response_data").isJsonNull) {
                dataList = jsonObject.getAsJsonArray("response_data")
            }
            item.code = resultCode
            item.message = message
            item.responseData = dataList
        }
        return item
    }
}
