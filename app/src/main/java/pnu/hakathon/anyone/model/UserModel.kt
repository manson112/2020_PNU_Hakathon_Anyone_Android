package pnu.hakathon.anyone.model

import pnu.hakathon.anyone.network.Request
import pnu.hakathon.anyone.network.ServerResponse


data class ReqBookmark(
    var data: Data = Data()
) : Request {
    constructor(userID: String) : this(Data(userID))

    data class Data(
        var userID: String = ""
    )

    override fun toMap(): HashMap<String, String> {
        return hashMapOf("userID" to data.userID)
    }
}

data class ReqSetBookmark(
    var data: Data = Data()
): Request {
    constructor(userID: String, storeID: String, checked:Boolean) : this(Data(userID, storeID, checked))
    data class Data(
        var userID: String = "",
        var storeID: String = "",
        var checked: Boolean = false
    )
    override fun toMap(): HashMap<String, String> {
        val m = hashMapOf<String, String>()
        m["userID"] = data.userID
        m["storeID"] = data.storeID
        m["checked"] = data.checked.toString()
        return m
    }
}

data class ReqSearchHistory(
    var data: Data = Data()
) : Request {
    constructor(userID: String) : this(Data(userID))

    data class Data(
        var userID: String = ""
    )

    override fun toMap(): HashMap<String, String> {
        return hashMapOf("userID" to data.userID)
    }
}

object User {
    fun requestBookmark(userID: String, success: (ServerResponse?) -> Unit, fail: () -> Unit) {
//        Log.d("Model.User", "requestBookmark is Called!")
//        try {
//            Network.service.requestBookmark(ReqBookmark(userID).toMap())
//                .enqueue(object : Callback<ServerResponse> {
//                    override fun onFailure(call: Call<ServerResponse>, t: Throwable) {
//                        Log.e("Model.User", t.message)
//                        fail()
//                    }
//
//                    override fun onResponse(
//                        call: Call<ServerResponse>,
//                        response: Response<ServerResponse>
//                    ) {
//                        Log.d("Model.User[RES]", response.body().toString())
//                        success(response.body())
//                    }
//                })
//
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
    }

    fun requestSearchHistory(userID: String, success: (ServerResponse?) -> Unit, fail: () -> Unit) {
//        Log.d("Model.User", "requestSearchHistory is Called!")
//        try {
//            Network.service.requestSearchHistory(ReqSearchHistory(userID).toMap())
//                .enqueue(object : Callback<ServerResponse> {
//                    override fun onFailure(call: Call<ServerResponse>, t: Throwable) {
//                        Log.e("Model.User", t.message)
//                        fail()
//                    }
//
//                    override fun onResponse(
//                        call: Call<ServerResponse>,
//                        response: Response<ServerResponse>
//                    ) {
//                        Log.d("Model.User[RES]", response.body().toString())
//                        success(response.body())
//                    }
//                })
//
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
    }
}