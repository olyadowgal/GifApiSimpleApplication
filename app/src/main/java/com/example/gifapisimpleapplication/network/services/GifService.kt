package com.example.gifapisimpleapplication.network.services

import com.example.gifapisimpleapplication.network.resoponses.GifResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GifService {

    @GET("search")
    fun getSearch(
        @Query("q") search: String,
        @Query("limit") limit: Int = 10,
        @Query("media_filter") mediaFilter: String = "minimal"
    ): Call<GifResponse>

}