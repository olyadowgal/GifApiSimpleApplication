package com.example.gifapisimpleapplication.viewmodels

import android.app.Application
import android.util.Log
import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.paging.PositionalDataSource
import com.example.gifapisimpleapplication.adapters.FeedItemsAdapter
import com.example.gifapisimpleapplication.datasource.GifsDataSource
import com.example.gifapisimpleapplication.entities.GifInfo
import com.example.gifapisimpleapplication.repositories.GifRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FeedViewModel(
    application: Application,
    gifRepository: GifRepository
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

    override fun onClick(gif: GifInfo) {
        TODO("Not yet implemented")
    }

    @MainThread
    fun onQueryChanged(query: String) {
        Log.d(TAG, query)
        this.query = query
        dataSourceFactory.invalidateLatestDataSource()
    }
}