package com.example.gifapisimpleapplication.network

import com.example.gifapisimpleapplication.network.services.GifService
import retrofit2.Retrofit

class ApiClient(retrofit: Retrofit) {

    val gifService: GifService by lazy {
        retrofit.create(GifService::class.java)
    }
}