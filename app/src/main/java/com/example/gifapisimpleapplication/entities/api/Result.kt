package com.example.gifapisimpleapplication.entities.api

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Result(
    val id: String,
    val media: List<Media>,
    val title: String
)