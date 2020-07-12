package pnu.hakathon.anyone.model

import com.google.gson.JsonArray
import com.google.gson.annotations.SerializedName

class ServerResponse(
    @SerializedName("code") var code: Int = 0,
    @SerializedName("message") var message: String? = null,
    @SerializedName("response_data") var responseData: JsonArray? = null
) {
    override fun toString(): String {
        return "ServerResponse(code=$code, message=$message, responseData=$responseData)"
    }
}
