package pnu.hakathon.anyone.network

import retrofit2.Call
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface RetrofitService {
    @POST("/user/bookmark")
    @FormUrlEncoded
    suspend fun requestBookmark(@FieldMap(encoded = true) data: HashMap<String, String>): ServerResponse

    @POST("/user/bookmark/put")
    @FormUrlEncoded
    suspend fun requestSetBookmark(@FieldMap(encoded = true) data: HashMap<String, String>): ServerResponse

    @POST("/user/search/history")
    @FormUrlEncoded
    suspend fun requestSearchHistory(@FieldMap(encoded = true) data: HashMap<String, String>): Call<ServerResponse>

    @POST("/store/info")
    @FormUrlEncoded
    suspend fun requestStoreInfo(@FieldMap(encoded = true) data: HashMap<String, String>): Call<ServerResponse>

    @POST("/store/home")
    @FormUrlEncoded
    suspend fun requestHome(@FieldMap(encoded = true) data: HashMap<String, String>): Call<ServerResponse>

    @POST("/store/near")
    @FormUrlEncoded
    suspend fun requestStoreMapNearMe(@FieldMap(encoded = true) data: HashMap<String, String>): ServerResponse

    @POST("/store/home/near")
    @FormUrlEncoded
    suspend fun requestStoreNearMe(@FieldMap(encoded = true) data: HashMap<String, String>): Call<ServerResponse>
}

