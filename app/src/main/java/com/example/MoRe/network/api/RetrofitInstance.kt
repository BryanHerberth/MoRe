package com.example.MoRe.network.api

import com.example.MoRe.dao.SessionManager
import com.example.MoRe.network.utils.Constants.Companion.BASE_URL
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitInstance {
    val authInterceptor = Interceptor{
        val requestBuilder = it.request().newBuilder()
        val token = SessionManager.accessToken
        requestBuilder.addHeader("Authorization", "Bearer $token")
        it.proceed(requestBuilder.build())
    }
    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(authInterceptor)
        .connectTimeout(120, TimeUnit.SECONDS)
        .readTimeout(120, TimeUnit.SECONDS)
        .build()
    private val retrofit by lazy{
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(AdapterFactory())
            .build()
    }

    val api: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}