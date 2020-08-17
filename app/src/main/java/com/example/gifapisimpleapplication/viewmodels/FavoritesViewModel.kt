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


    override fun onClick(gif: GifInfo) {
        TODO("Not yet implemented")
    }

}