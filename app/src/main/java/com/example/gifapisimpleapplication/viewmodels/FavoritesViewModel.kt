package com.example.gifapisimpleapplication.viewmodels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.gifapisimpleapplication.adapters.FeedItemsAdapter
import com.example.gifapisimpleapplication.datasource.GifsDataSource
import com.example.gifapisimpleapplication.entities.GifInfo
import com.example.gifapisimpleapplication.repositories.GifRepository

class FavoritesViewModel(
    application: Application,
    gifRepository: GifRepository
) : BaseViewModel(
    application
), FeedItemsAdapter.Callback {


    private val dataSourceFactory = gifRepository.getAllFavorites()

    val data: LiveData<PagedList<GifInfo>> = LivePagedListBuilder(
        dataSourceFactory,
        PagedList.Config.Builder()
            .setPageSize(10)
            .setEnablePlaceholders(false)
            .build()
    ).build()

    override fun onGifClick(gif: GifInfo) {
        TODO("Not yet implemented")
    }

    override fun onAddToFavoritesClick(gif: GifInfo) {
        TODO("Not yet implemented")
    }

}