package com.example.gifapisimpleapplication.entities.api

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Gif(
    val dims: List<Int>,
    val preview: String,
    val size: Int,
    val url: String
)