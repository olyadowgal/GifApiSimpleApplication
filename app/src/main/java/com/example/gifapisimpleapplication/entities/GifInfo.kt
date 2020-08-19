package com.example.gifapisimpleapplication.entities

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "Favorites")
data class GifInfo(
    @PrimaryKey
    val id: String,
    val title: String,
    val url: String,
    val preview: String,
    var isFavorite: Boolean
) : Parcelable {

    @IgnoredOnParcel
    val fileName
        get() = "$id.gif"

    companion object {

        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<GifInfo>() {

            override fun areItemsTheSame(
                oldItem: GifInfo,
                newItem: GifInfo
            ): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: GifInfo,
                newItem: GifInfo
            ): Boolean = oldItem == newItem
        }
    }
}