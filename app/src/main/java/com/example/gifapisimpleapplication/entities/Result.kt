package com.example.gifapisimpleapplication.entities


import com.squareup.moshi.Json

data class Result(
    @Json(name = "id")
    val id: String,
    @Json(name = "media")
    val media: List<Media>,
    @Json(name = "shares")
    val shares: Int,
    @Json(name = "tags")
    val tags: List<Any>,
    @Json(name = "title")
    val title: String,
    @Json(name = "url")
    val url: String
)