package com.example.gifapisimpleapplication.viewmodels

import android.app.Application
import android.util.Log
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
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

    companion object {
        private val TAG: String = FeedViewModel::class.java.simpleName
    }

    //TODO: refactor this
    private val dataSource = object : PositionalDataSource<GifInfo>() {

        private val query = "cats"//TODO: move to constructor

        override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<GifInfo>) {
            Log.d(TAG, "loadRange($params, $callback)")
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
            Log.d(TAG, "loadInitial($params, $callback)")
            GlobalScope.launch {
                val gifs = gifRepository.fetchGifs(
                    search = query,
                    limit = params.requestedLoadSize,
                    pos = params.requestedStartPosition
                )
                callback.onResult(gifs, params.requestedStartPosition)
            }
        }
    }

    //TODO: refactor this
    private val dataSourceFactory = object : DataSource.Factory<Int, GifInfo>() {

        override fun create(): DataSource<Int, GifInfo> = dataSource
    }

    //TODO: refactor this
    val data = LivePagedListBuilder(
        dataSourceFactory,
        PagedList.Config.Builder()
            .setPageSize(10)
            .setEnablePlaceholders(false)
            .build()
    ).build()

    override fun onClick(gif: GifInfo) {
        TODO("Not yet implemented")
    }
}