package pnu.hakathon.anyone.model

import android.util.Log
import pnu.hakathon.anyone.network.Network
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


data class ReqStoreInfo(
    var data: Data = Data()
) : Request {
    constructor(storeID: String) : this(Data(storeID))

    data class Data(
        var storeID: String = ""
    )

    override fun toMap(): HashMap<String, String> {
        return hashMapOf("store_id" to data.storeID)
    }
}

data class ReqHome(
    var data: Data = Data()
) : Request {
    constructor(categoryID: String) : this(Data(categoryID))

    data class Data(
        var categoryID: String = ""
    )

    override fun toMap(): HashMap<String, String> {
        return hashMapOf("category_id" to data.categoryID)
    }
}

object StoreModel {
    fun requestStoreInfo(storeID: String, success: (ServerResponse?) -> Unit, fail: () -> Unit) {
        Log.d("Model.StoreModel", "requestStoreInfo is Called!")
        try {
            Network.service.requestStoreInfo(ReqStoreInfo(storeID).toMap()).enqueue(object :
                Callback<ServerResponse> {
                override fun onFailure(call: Call<ServerResponse>, t: Throwable) {
                    Log.e("Model.StoreModel", t.message)
                    fail()
                }

                override fun onResponse(
                    call: Call<ServerResponse>,
                    response: Response<ServerResponse>
                ) {
                    Log.d("Model.StoreModel[RES]", response.body().toString())
                    success(response.body())
                }
            })

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun requestHome(categoryID: String, success: (ServerResponse?) -> Unit, fail: () -> Unit) {
        Log.d("Model.StoreModel", "requestHome is Called!")

        try {
            Network.service.requestHome(ReqHome(categoryID).toMap())
                .enqueue(object : Callback<ServerResponse> {
                    override fun onFailure(call: Call<ServerResponse>, t: Throwable) {
                        Log.e("Model.StoreModel", t.message)
                        fail()
                    }

                    override fun onResponse(
                        call: Call<ServerResponse>,
                        response: Response<ServerResponse>
                    ) {
                        Log.d("Model.StoreModel[RES]", response.body().toString())
                        success(response.body())
                    }
                })
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}