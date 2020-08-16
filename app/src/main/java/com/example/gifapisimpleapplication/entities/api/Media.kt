package com.example.gifapisimpleapplication.entities.api

import com.example.gifapisimpleapplication.entities.api.Gif
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Media(
    val gif: Gif
)