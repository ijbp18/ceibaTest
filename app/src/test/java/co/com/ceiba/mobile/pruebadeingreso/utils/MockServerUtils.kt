package co.com.ceiba.mobile.pruebadeingreso.utils

import co.com.ceiba.mobile.pruebadeingreso.data.network.APIService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun createInternalService(baseUrl: String): APIService {
    val okHttpClient = OkHttpClient
        .Builder()
        .build()
    return Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .build().create(APIService::class.java)
}
