package com.example.gifapisimpleapplication.entities

data class GifInfo(
    val id: String,
    val title: String,
    val url: String,
    val preview: String,
    val dims: List<Int>
)