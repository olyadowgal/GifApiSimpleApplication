package com.example.gifapisimpleapplication.datasource

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PositionalDataSource
import com.example.gifapisimpleapplication.entities.GifInfo
import com.example.gifapisimpleapplication.repositories.GifRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception

class GifsDataSource(
    private val gifRepository: GifRepository,
    private val query: String?,
    private val onError: (Exception) -> Unit,
    private val onLoadingChanged: (isLoading: Boolean) -> Unit
) : PositionalDataSource<GifInfo>() {

    companion object {
        private val TAG: String = GifsDataSource::class.java.simpleName
    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<GifInfo>) {
        Log.d(TAG, "loadRange($params, $callback)")
        GlobalScope.launch {
            try {
                onLoadingChanged(true)
                val gifs = gifRepository.fetchGifs(
                    search = query,
                    limit = params.loadSize,
                    pos = params.startPosition
                )
                callback.onResult(gifs)
            } catch (e: Exception) {
                onError(e)
                callback.onResult(emptyList())
            } finally {
                onLoadingChanged(false)
            }
        }
    }

    override fun loadInitial(
        params: LoadInitialParams,
        callback: LoadInitialCallback<GifInfo>
    ) {
        Log.d(TAG, "loadInitial($params, $callback)")
        GlobalScope.launch {
            try {
                onLoadingChanged(true)
                val gifs = gifRepository.fetchGifs(
                    search = query,
                    limit = params.requestedLoadSize,
                    pos = params.requestedStartPosition
                )
                callback.onResult(gifs, params.requestedStartPosition)
            } catch (e: Exception) {
                onError(e)
                callback.onResult(emptyList(), 0)
            } finally {
                onLoadingChanged(false)
            }
        }
    }

    class Factory(
        private val gifRepository: GifRepository,
        private val getQuery: () -> String?,
        private val onError: (Exception) -> Unit,
        private val onLoadingChanged: (isLoading: Boolean) -> Unit
    ) : DataSource.Factory<Int, GifInfo>() {

        private val _isLoading = MediatorLiveData<Boolean>()
        val isLoading: LiveData<Boolean> = _isLoading

        private var latestDataSource: GifsDataSource? = null

        override fun create(): DataSource<Int, GifInfo> =
            GifsDataSource(
                gifRepository,
                getQuery(),
                onError,
                onLoadingChanged
            ).also { latestDataSource = it }

        fun invalidateLatestDataSource() {
            latestDataSource?.invalidate()
        }
    }
}