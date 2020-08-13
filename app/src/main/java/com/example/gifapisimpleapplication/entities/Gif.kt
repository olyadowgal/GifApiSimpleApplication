package com.example.gifapisimpleapplication.entities


import com.squareup.moshi.Json

data class Gif(
    @Json(name = "dims")
    val dims: List<Int>,
    @Json(name = "preview")
    val preview: String,
    @Json(name = "size")
    val size: Int,
    @Json(name = "url")
    val url: String
)