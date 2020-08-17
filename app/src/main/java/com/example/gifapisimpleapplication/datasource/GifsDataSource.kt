package com.example.gifapisimpleapplication.datasource

import android.util.Log
import androidx.paging.DataSource
import androidx.paging.PositionalDataSource
import com.example.gifapisimpleapplication.entities.GifInfo
import com.example.gifapisimpleapplication.repositories.GifRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class GifsDataSource(
    private val gifRepository: GifRepository,
    private val query: String?
) : PositionalDataSource<GifInfo>() {

    companion object {
        private val TAG: String = GifsDataSource::class.java.simpleName
    }

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

    class Factory(
        private val gifRepository: GifRepository,
        private val getQuery: () -> String?
    ) : DataSource.Factory<Int, GifInfo>() {

        private var latestDataSource: GifsDataSource? = null

        override fun create(): DataSource<Int, GifInfo> =
            GifsDataSource(gifRepository, getQuery()).also { latestDataSource = it }

        fun invalidateLatestDataSource() {
            latestDataSource?.invalidate()
        }
    }
}