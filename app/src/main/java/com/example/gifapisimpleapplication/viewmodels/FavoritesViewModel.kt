package com.example.gifapisimpleapplication.viewmodels

import android.app.Application
import com.example.gifapisimpleapplication.adapters.FeedItemsAdapter
import com.example.gifapisimpleapplication.entities.GifInfo
import com.example.gifapisimpleapplication.repositories.GifRepository

class FavoritesViewModel(
    application: Application,
    gifRepository: GifRepository
) : BaseViewModel(
    application
), FeedItemsAdapter.Callback {


    override fun onGifClick(gif: GifInfo) {
        TODO("Not yet implemented")
    }

    override fun onAddToFavoritesClick(gif: GifInfo) {
        TODO("Not yet implemented")
    }

}