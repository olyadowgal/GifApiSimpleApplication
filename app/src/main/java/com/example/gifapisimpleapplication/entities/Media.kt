package com.example.gifapisimpleapplication.entities


import com.squareup.moshi.Json

data class Media(
    @Json(name = "gif")
    val gif: Gif
)