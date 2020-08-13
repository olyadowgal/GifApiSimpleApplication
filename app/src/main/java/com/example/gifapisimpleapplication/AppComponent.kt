package com.example.gifapisimpleapplication

import android.app.Application
import com.example.gifapisimpleapplication.network.ApiClient
import com.example.gifapisimpleapplication.network.interceptors.AuthInterceptor
import com.readystatesoftware.chuck.ChuckInterceptor
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object AppComponent {

    lateinit var application: Application
    lateinit var moshi: Moshi
    lateinit var okHttpClient : OkHttpClient
    lateinit var retrofit: Retrofit

    val apiClient by lazy { ApiClient(retrofit) }

    fun init(application: Application) {

        this.application = application

        moshi = Moshi.Builder().build()

        okHttpClient = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .protocols(listOf(Protocol.HTTP_1_1))
            .addInterceptor(AuthInterceptor())
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

    }
}