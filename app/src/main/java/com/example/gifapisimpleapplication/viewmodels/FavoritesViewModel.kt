package com.example.gifapisimpleapplication.viewmodels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.gifapisimpleapplication.adapters.FeedItemsAdapter
import com.example.gifapisimpleapplication.datasource.GifsDataSource
import com.example.gifapisimpleapplication.entities.GifInfo
import com.example.gifapisimpleapplication.repositories.GifRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FavoritesViewModel(
    application: Application,
    private val gifRepository: GifRepository
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
        if (gif.isFavorite) {
            GlobalScope.launch {
                gifRepository.deleteFromFavorites(gif)
            }
        } else {
            GlobalScope.launch {
                gifRepository.insertToFavorites(gif)
            }
        }
    }

}