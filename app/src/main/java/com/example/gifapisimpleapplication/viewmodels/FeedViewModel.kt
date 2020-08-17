package com.example.gifapisimpleapplication.viewmodels

import android.app.Application
import android.util.Log
import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.paging.PositionalDataSource
import com.example.gifapisimpleapplication.adapters.FeedItemsAdapter
import com.example.gifapisimpleapplication.datasource.GifsDataSource
import com.example.gifapisimpleapplication.entities.GifInfo
import com.example.gifapisimpleapplication.entities.api.Gif
import com.example.gifapisimpleapplication.repositories.GifRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FeedViewModel(
    application: Application,
    private val gifRepository: GifRepository
) : BaseViewModel(
    application
), FeedItemsAdapter.Callback {

    companion object {
        private val TAG: String = FeedViewModel::class.java.simpleName
    }


    private var query: String? = null
        set(value) {
            field = value
            dataSourceFactory.invalidateLatestDataSource()
        }

    private val dataSourceFactory = GifsDataSource.Factory(gifRepository) { query }

    val data: LiveData<PagedList<GifInfo>> = LivePagedListBuilder(
        dataSourceFactory,
        PagedList.Config.Builder()
            .setPageSize(10)
            .setEnablePlaceholders(false)
            .build()
    ).build()


    @MainThread
    fun onQueryChanged(query: String) {
        Log.d(TAG, query)
        this.query = query
        dataSourceFactory.invalidateLatestDataSource()
    }

    override fun onGifClick(gif: GifInfo) {
        TODO("Not yet implemented")
    }

    override fun onAddToFavoritesClick(gif: GifInfo) {
        if (gif.isFavorite) {
            GlobalScope.launch {
                gifRepository.insertToFavorites(gif)
            }
        } else {
            GlobalScope.launch {
                gifRepository.deleteFromFavorites(gif)
            }
        }

    }
}