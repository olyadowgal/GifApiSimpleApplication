package com.example.gifapisimpleapplication.entities

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Media(
    val gif: Gif
)