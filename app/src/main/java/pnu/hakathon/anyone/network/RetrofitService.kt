package pnu.hakathon.anyone.network

import pnu.hakathon.anyone.model.ServerResponse
import retrofit2.Call
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface RetrofitService {
    @POST("/user/bookmark")
    @FormUrlEncoded
    fun requestBookmark(@FieldMap(encoded = true) data: HashMap<String, String>): Call<ServerResponse>

    @POST("/user/search/history")
    @FormUrlEncoded
    fun requestSearchHistory(@FieldMap(encoded = true) data: HashMap<String, String>): Call<ServerResponse>

    @POST("/store/info")
    @FormUrlEncoded
    fun requestStoreInfo(@FieldMap(encoded = true) data: HashMap<String, String>): Call<ServerResponse>

    @POST("/store/home")
    @FormUrlEncoded
    fun requestHome(@FieldMap(encoded = true) data: HashMap<String, String>): Call<ServerResponse>
}

