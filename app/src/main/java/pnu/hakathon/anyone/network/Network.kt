package pnu.hakathon.anyone.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Network {
    val host = "http://10.0.2.2:8080/"
    val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(host)
        .build()
    val service = retrofit.create(RetrofitService::class.java)
}