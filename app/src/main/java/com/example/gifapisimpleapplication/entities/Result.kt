package com.example.gifapisimpleapplication.entities

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Result(
    val id: String,
    val media: List<Media>,
    val shares: Int,
    val tags: List<Any>,
    val title: String,
    val url: String
)