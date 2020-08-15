package com.example.gifapisimpleapplication.network.resoponses


import com.example.gifapisimpleapplication.entities.api.Result
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GifResponse(
    @Deprecated("Calculated field")
    val next: String,
    val results: List<Result>
)