package com.example.gifapisimpleapplication

import android.app.Application
import com.example.gifapisimpleapplication.network.ApiClient
import com.example.gifapisimpleapplication.network.interceptors.AuthInterceptor
import com.example.gifapisimpleapplication.repositories.GifRepository
import com.example.gifapisimpleapplication.viewmodels.ViewModelFactory
import com.readystatesoftware.chuck.ChuckInterceptor
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object AppComponent {

    private lateinit var application: Application

    private val moshi by lazy {
        Moshi.Builder().build()
    }

    private val okHttpClient by lazy {
        OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .protocols(listOf(Protocol.HTTP_1_1))
            .addInterceptor(AuthInterceptor())
            .build()
    }

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    private val apiClient by lazy { ApiClient(retrofit) }

    private val gifRepository: GifRepository by lazy {
        GifRepository(apiClient)
    }

    val viewModelFactory by lazy {
        ViewModelFactory(application,gifRepository)
    }

    fun init(application: Application) {
        this.application = application
    }
}