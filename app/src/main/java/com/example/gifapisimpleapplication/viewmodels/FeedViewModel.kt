package com.example.gifapisimpleapplication.viewmodels

import android.app.Application
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PositionalDataSource
import com.example.gifapisimpleapplication.adapters.FeedItemsAdapter
import com.example.gifapisimpleapplication.entities.GifInfo
import com.example.gifapisimpleapplication.repositories.GifRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FeedViewModel(
    application: Application,
    private val gifRepository: GifRepository
) : BaseViewModel(
    application
), FeedItemsAdapter.Callback {

    //TODO: refactor this
    private val dataSource = object : PositionalDataSource<GifInfo>() {

        private val query = "cats"//TODO: move to constructor

        override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<GifInfo>) {
            GlobalScope.launch {
                val gifs = gifRepository.fetchGifs(
                    search = query,
                    limit = params.loadSize,
                    pos = params.startPosition
                )
                callback.onResult(gifs)
            }
        }

        override fun loadInitial(
            params: LoadInitialParams,
            callback: LoadInitialCallback<GifInfo>
        ) {
            GlobalScope.launch {
                val gifs = gifRepository.fetchGifs(
                    search = query,
                    limit = params.requestedLoadSize,
                    pos = params.requestedStartPosition
                )
                callback.onResult(gifs, params.requestedStartPosition, gifs.size)
            }
        }
    }

    //TODO: refactor this
    private val dataSourceFactory = object : DataSource.Factory<Int, GifInfo>() {

        override fun create(): DataSource<Int, GifInfo> = dataSource
    }

    //TODO: refactor this
    val data = LivePagedListBuilder(dataSourceFactory, 10).build()

    fun init(isFavorite: Boolean) {

    }

    override fun onClick(gif: GifInfo) {
        TODO("Not yet implemented")
    }
}