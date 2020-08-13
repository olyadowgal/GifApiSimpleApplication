package com.example.gifapisimpleapplication.network.resoponses


import com.example.gifapisimpleapplication.entities.Result
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GifResponse(
    @Json(name = "next")
    val next: String,
    @Json(name = "results")
    val results: List<Result>
)