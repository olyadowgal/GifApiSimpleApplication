package com.example.gifapisimpleapplication.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Favorites")
data class GifInfo(
    @PrimaryKey
    val id: String,
    val title: String,
    val url: String,
    val preview: String,
    val isFavorite : Boolean
)