package com.example.gifapisimpleapplication.network.interceptors

import com.example.gifapisimpleapplication.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(
            chain.request().newBuilder().url(
                chain.request().url.newBuilder()
                    .addQueryParameter("key", BuildConfig.API_KEY)
                    .build()
            ).build()
        )
    }
}